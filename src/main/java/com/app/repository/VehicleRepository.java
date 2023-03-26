package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, Integer>{

}
