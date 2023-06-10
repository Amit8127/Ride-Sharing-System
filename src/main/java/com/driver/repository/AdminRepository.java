package com.driver.repository;

import com.driver.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.driver.model.Admin;

import javax.xml.bind.Marshaller;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer>{

    boolean existsById(Integer adminId);


}
