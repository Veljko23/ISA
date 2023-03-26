package com.app.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Admin;
import com.app.repository.AdminRepository;

@Service
public class AdminService {
	
	@Autowired
	private AdminRepository adminRepository;
	
	public Admin findOne(Integer id) {
		return adminRepository.findById(id).orElseGet(null);
	} 
	
	public Admin save(Admin admin) {
		return adminRepository.save(admin);
	}

}
