package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Path;
import com.app.repository.PathRepository;

@Service
public class PathService {
	
	@Autowired
	private PathRepository pathRepository;
	
	public Path findOne(Integer id) {
		return pathRepository.findById(id).orElseGet(null);
	}
	
	public List<Path> findAll(){
		return pathRepository.findAll();
	}
	
	public Path save(Path path) {
		return pathRepository.save(path);
	}
	
	public void remove(Integer id) {
		pathRepository.deleteById(id);
	}

}
