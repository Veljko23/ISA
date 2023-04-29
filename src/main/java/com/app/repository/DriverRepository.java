package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer>{
	
	@Query("select d from USERS d join fetch d.drivings dr where d.id =?1")
    public Driver getOneWithDrivings(Integer id);
}
