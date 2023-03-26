package com.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.model.WorkTime;

public interface WorkTimeRepository extends JpaRepository<WorkTime, Integer>{

}
