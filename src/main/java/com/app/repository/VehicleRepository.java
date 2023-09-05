package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

	@Query("select v from Vehicle v left join v.driver d where d.active=true")
	public List<Vehicle> findAllWithDrivers();
}
