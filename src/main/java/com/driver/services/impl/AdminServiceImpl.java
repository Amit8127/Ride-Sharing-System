package com.driver.services.impl;

import java.util.List;

import com.driver.Exceptions.AdminNotFound;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.driver.model.Admin;
import com.driver.model.Customer;
import com.driver.model.Driver;
import com.driver.repository.AdminRepository;
import com.driver.repository.CustomerRepository;
import com.driver.repository.DriverRepository;
@Service
public class AdminServiceImpl implements AdminService {
	@Autowired
	AdminRepository adminRepository1;
	@Autowired
	DriverRepository driverRepository1;
	@Autowired
	CustomerRepository customerRepository1;
	@Override
	public void adminRegister(Admin admin) {
		//Save the admin in the database
		adminRepository1.save(admin);
	}

	@Override
	public Admin updatePassword(Integer adminId, String password) throws AdminNotFound{
		//Update the password of admin with given id
		if(!adminRepository1.existsById(adminId)) {
			throw new AdminNotFound(adminId);
		}
		Admin admin = adminRepository1.getOne(adminId);
		admin.setPassword(password);
		adminRepository1.save(admin);
		return admin;
	}

	@Override
	public void deleteAdmin(int adminId) throws AdminNotFound{
		// Delete admin without using deleteById function
		if(!adminRepository1.existsById(adminId)) {
			throw new AdminNotFound(adminId);
		}
		Admin admin = adminRepository1.getOne(adminId);
		adminRepository1.delete(admin);
	}

	@Override
	public List<Driver> getListOfDrivers() {
		//Find the list of all drivers
		List<Driver> drivers = driverRepository1.findAll();
		return drivers;
	}

	@Override
	public List<Customer> getListOfCustomers() {
		//Find the list of all customers
		List<Customer> customers = customerRepository1.findAll();
		return customers;
	}
}