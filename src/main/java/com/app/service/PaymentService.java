package com.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.model.Payment;
import com.app.repository.PaymentRepository;

@Service
public class PaymentService {

	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment findOne(Integer id) {
		return paymentRepository.findById(id).orElseGet(null);
	}
	
	public List<Payment> findAll(){
		return paymentRepository.findAll();
	}
	
	public Payment save(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public void remove(Integer id) {
		paymentRepository.deleteById(id);
	}
}
