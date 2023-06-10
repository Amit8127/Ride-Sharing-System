# Ride-sharing System API's

This is built using **Rest Api's** and using **MVC structure** and other **Microservices.**

This is a README file that provides an overview of the code for a Ride-sharing System. The system consists of several entities, including Admins, Customers, Drivers, Cabs, and Trip Bookings. Below is a description of each entity and its associated functionalities.

**The system have the following features:**

# Admin

The Admin entity is responsible for managing the system. It has the following functionalities:

* Register new admins
* Update the password of existing admins
* Delete existing admins

# Customer

The Customer entity represents the users of the ride-sharing service. It provides the following functionalities:

* Register new customers
* Delete existing customers
* Book trips
* Assign the nearest available driver and cab to the customer when booking a trip
* Cancel or complete a trip

# Driver
The Driver entity represents the drivers who provide transportation services. It has the following functionalities:

* Register new drivers
* Delete existing drivers
* Update the status of their cab (available or unavailable)

# Cab

The Cab entity stores information about the available cabs. It includes the following information:

* Per-kilometer rate
* Availability status of the cab (available or unavailable)

# TripBooking

The TripBooking entity stores information about the trips booked by customers. It includes the following information:

* From and to locations
* Distance
* Status of the trip (ongoing, completed, cancelled)
* Bill

# Code Organization

The code for this ride-sharing system can be organized into **separate classes or packages** for each entity, along with any necessary helper classes or functions. The code follow **Object-Oriented Programming principles** and best practices such as encapsulation, modularity, and proper separation of concerns.

# Running the Code

To run the ride-sharing system, you can compile and execute the Main class using a **Java compiler and interpreter**. Make sure you have the necessary **Java development environment set up**.
