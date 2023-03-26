package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.WorkTime;
import com.app.repository.WorkTimeRepository;

@Service
public class WorkTimeService {
	
	@Autowired
	private WorkTimeRepository workTimeRepository;
	
	public WorkTime findOne(Integer id) {
		return workTimeRepository.findById(id).orElseGet(null);
	}
	
	public List<WorkTime> findAll(){
		return workTimeRepository.findAll();
	}
	
	public WorkTime save(WorkTime workTime) {
		return workTimeRepository.save(workTime);
	}
	
	public void remove(Integer id) {
		workTimeRepository.deleteById(id);
	}

}
