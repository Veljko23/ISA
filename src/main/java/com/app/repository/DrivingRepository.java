package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.Driving;

public interface DrivingRepository extends JpaRepository<Driving, Integer>{
	public List<Driving> findByOrderByStartAsc();
	
	@Query(value = "select d.* from Driving d, DRIVING_PASS dp where d.id = dp.driving_id and dp.pass_id = ?1 order by d.price asc", nativeQuery = true)
	public List<Driving> findAllForPassengerOrderByPriceAsc(Integer passengerId);
	
	@Query(value = "select d.* from Driving d, DRIVING_PASS dp where d.id = dp.driving_id and dp.pass_id = ?1 order by d.price desc", nativeQuery = true)
	public List<Driving> findAllForPassengerOrderByPriceDesc(Integer passengerId);
	
	@Query(value = "select d.* from Driving d, DRIVING_PASS dp where d.id = dp.driving_id and dp.pass_id = ?1 order by d.start asc", nativeQuery = true)
	public List<Driving> findAllForPassengerOrderByStartAsc(Integer passengerId);
	
	@Query(value = "select d.* from Driving d, DRIVING_PASS dp where d.id = dp.driving_id and dp.pass_id = ?1 order by d.start desc", nativeQuery = true)
	public List<Driving> findAllForPassengerOrderByStartDesc(Integer passengerId);
	
	@Query(value = "select d.* from Driving d, DRIVING_PASS dp where d.id = dp.driving_id and dp.pass_id = ?1 order by d.end asc", nativeQuery = true)
	public List<Driving> findAllForPassengerOrderByEndAsc(Integer passengerId);
	
	@Query(value = "select d.* from Driving d, DRIVING_PASS dp where d.id = dp.driving_id and dp.pass_id = ?1 order by d.end desc", nativeQuery = true)
	public List<Driving> findAllForPassengerOrderByEndDesc(Integer passengerId);
	
	@Query(value = "select d from Driving d join fetch d.passengers p where p.id =?1 order by d.?2 ?3", nativeQuery = true)
	public List<Driving> findAllForPassengerOrderBy(Integer passengerId, String field, String order);
	
	
	
	
	@Query("select d from Driving d join fetch d.driver dr where dr.id =?1")
	public List<Driving> findAllForDriver(Integer driverId);
	
	@Query(value = "select d.* from Driving d, DRIVING_PASS dp where dp.driving_id = d.id and d.driver_id = ?1 order by d.price asc", nativeQuery = true)
	public List<Driving> findAllForDriverOrderByPriceAsc(Integer driverId);
	
	@Query(value = "select d.* from Driving d where d.driver_id = ?1 order by d.price desc", nativeQuery = true)
	public List<Driving> findAllForDriverOrderByPriceDesc(Integer driverId);
	
	@Query(value = "select d.* from Driving d where d.driver_id = ?1 order by d.start asc", nativeQuery = true)
	public List<Driving> findAllForDriverOrderByStartAsc(Integer driverId);
	
	@Query(value = "select d.* from Driving d where d.driver_id = ?1 order by d.start desc", nativeQuery = true)
	public List<Driving> findAllForDriverOrderByStartDesc(Integer driverId);
	
	@Query(value = "select d.* from Driving d where d.driver_id = ?1 order by d.end asc", nativeQuery = true)
	public List<Driving> findAllForDriverOrderByEndAsc(Integer driverId);
	
	@Query(value = "select d.* from Driving d where d.driver_id = ?1 order by d.end desc", nativeQuery = true)
	public List<Driving> findAllForDriverOrderByEndDesc(Integer driverId);

	
	
	@Query("select d from Driving d join fetch d.paths p where d.id =?1")
	public Driving findOneWithPaths(Integer id);
	
	@Query(value = "select d.* from Driving d, driving_pass dp, driving_paths paths where d.id =?1 and d.id = dp.driving_id and paths.driving_id = d.id", nativeQuery = true)
	public Driving getOneWithPathsAndPassengers(Integer id);
}
