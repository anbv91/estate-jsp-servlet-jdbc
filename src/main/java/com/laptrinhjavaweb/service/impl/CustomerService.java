package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dto.CustomerDTO;
import com.laptrinhjavaweb.entity.CustomerEntity;
import com.laptrinhjavaweb.repository.ICustomerRepository;
import com.laptrinhjavaweb.repository.impl.CustomerRepository;
import com.laptrinhjavaweb.service.ICustomerService;

public class CustomerService implements ICustomerService{

	ICustomerRepository customerRepository = new CustomerRepository();
	@Override
	public List<CustomerDTO> findAll() {
		
		List<CustomerDTO> results= new ArrayList<>();
		List<CustomerEntity> customerEntites=customerRepository.findAll();
		
		for(CustomerEntity item: customerEntites) {
			CustomerDTO customerDTO= new CustomerDTO();
			
			customerDTO.setFullname(item.getFullName());
			customerDTO.setPhone(item.getPhone());
			customerDTO.setEmail(item.getEmail());
			results.add(customerDTO);
		}
		
		return results;
	}

}
