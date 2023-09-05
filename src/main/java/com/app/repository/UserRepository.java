package com.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByEmail(String email);
	@Query(value = "select u.* from USERS u where u.type IS NOT 'A'", nativeQuery = true)
	List<User> findByRole();
}
