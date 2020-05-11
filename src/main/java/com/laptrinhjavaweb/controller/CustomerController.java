package com.laptrinhjavaweb.controller;

import java.util.List;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.service.ICustomerService;
import com.laptrinhjavaweb.service.impl.CustomerService;

public class CustomerController {
	
	static final String DB_URL = "jdbc:mysql://localhost:3306/estate32020module1part1";
	static final String USER = "root";
	static final String PASS = "1234";
	
	public static void main(String[] args) {
		
		ICustomerService customerService = new CustomerService();
		
		List<CustomerDTO> results = customerService.findAll();
		for (CustomerDTO item : results) {
			System.out.println("Họ Và Tên: " + item.getFullname());
			System.out.println("Số Điện Thoại: " + item.getPhone());
			System.out.println("Email " + item.getEmail());
			System.out.println("------------------");
		}
		

	}

}
