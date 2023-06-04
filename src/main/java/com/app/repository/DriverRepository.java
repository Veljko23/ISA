package com.app.repository;

import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Driver;

public interface DriverRepository extends JpaRepository<Driver, Integer>{
	
	@Query(value = "select d from Driver d join fetch d.drivings dr where d.id = ?1")
	public Driver getOneWithDrivings(Integer id);
	
	@Query(value = "select d from Driver d join fetch d.drivings dr") //za admina
	public Set<Driver> getAllWithDrivings();
	
//	@Query(value = "select u.* from USERS u, driving_pass dp, driving_paths dpths where u.id = dp.pass_id and dp.driving_id=dpths.driving_id", nativeQuery = true)
//	public Set<Driver> getAllWithDrivings();
}
