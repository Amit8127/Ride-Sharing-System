package com.driver.services.impl;

import com.driver.Exceptions.NoCabAvailable;
import com.driver.model.TripBooking;
import com.driver.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
import com.driver.repository.TripBookingRepository;
import com.driver.model.TripStatus;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerRepository2;

	@Autowired
	DriverRepository driverRepository2;

	@Autowired
	TripBookingRepository tripBookingRepository2;

	@Override
	public void register(Customer customer) {
		//Save the customer in database
		customerRepository2.save(customer);
	}

	@Override
	public void deleteCustomer(Integer customerId) {
		// Delete customer without using deleteById function
		Customer customer = customerRepository2.getOne(customerId);
		customerRepository2.delete(customer);
	}

	@Override
	public TripBooking bookTrip(int customerId, String fromLocation, String toLocation, int distanceInKm) throws Exception{
		//Book the driver with lowest driverId who is free (cab available variable is Boolean.TRUE). If no driver is available, throw "No cab available!" exception
		//Avoid using SQL query
		Driver avaDriver = null;
		int loDrId = Integer.MAX_VALUE;
		List<Driver> drivers = driverRepository2.findAll();
		for(Driver driver : drivers) {
			if(driver.getCab().getAvailable() == Boolean.TRUE) {
				loDrId = Math.min(loDrId, driver.getDriverId());
			}
		}
		if(loDrId != Integer.MAX_VALUE) {
			avaDriver = driverRepository2.getOne(loDrId);
		}
		if(avaDriver == null) {
			throw new NoCabAvailable();
		}

		TripBooking tripBooking = new TripBooking();
		Customer customer = customerRepository2.getOne(customerId);
		avaDriver.getCab().setAvailable(Boolean.FALSE);
		int retParKm = avaDriver.getCab().getPerKmRate();

		tripBooking.setCustomer(customer);
		tripBooking.setDriver(avaDriver);
		tripBooking.setFromLocation(fromLocation);
		tripBooking.setToLocation(toLocation);
		tripBooking.setDistanceInKm(distanceInKm);
		tripBooking.setBill(distanceInKm * retParKm);
		tripBooking.setTripStatus(TripStatus.CONFIRMED);

		customer.getTripBookings().add(tripBooking);
		customerRepository2.save(customer);

		avaDriver.getTripBookings().add(tripBooking);
		driverRepository2.save(avaDriver);

		return tripBooking;
	}

	@Override
	public void cancelTrip(Integer tripId){
		//Cancel the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.getOne(tripId);
		tripBooking.setTripStatus(TripStatus.CANCELED);

		tripBooking.getDriver().getCab().setAvailable(Boolean.TRUE);
		tripBooking.setBill(0);

		tripBookingRepository2.save(tripBooking);
	}

	@Override
	public void completeTrip(Integer tripId){
		//Complete the trip having given trip Id and update TripBooking attributes accordingly
		TripBooking tripBooking = tripBookingRepository2.getOne(tripId);
		tripBooking.setTripStatus(TripStatus.COMPLETED);

		int bill = tripBooking.getDriver().getCab().getPerKmRate() * tripBooking.getDistanceInKm();
		tripBooking.setBill(bill);

		tripBooking.getDriver().getCab().setAvailable(Boolean.TRUE);
		tripBookingRepository2.save(tripBooking);
	}
}
