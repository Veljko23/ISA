package com.app.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Integer>{
	
	@Query(value = "select u from Passenger u join fetch u.drivings d where u.id = ?1")
	public Passenger getOneWithDrivings(Integer id);
	
	@Query(value = "select u from Passenger u join fetch u.drivings d")
	public Set<Passenger> getAllWithDrivings();
}
