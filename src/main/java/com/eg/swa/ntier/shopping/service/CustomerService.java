package com.eg.swa.ntier.shopping.service;

import java.util.Optional;

import com.eg.swa.ntier.shopping.dto.CustomerDto;
import com.eg.swa.ntier.shopping.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eg.swa.ntier.shopping.model.Customer;
import com.eg.swa.ntier.shopping.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	public Optional<Customer> getCustomerById(Long customerId) {
		return customerRepository.findById(customerId);
	}

	public void CreateCustomer(CustomerDto customerDto){
		Optional<Customer> optionalCustomer = customerRepository.findByEmail(customerDto.getEmail());
		if(optionalCustomer.isPresent()){
			throw new RuntimeException("Customer Has Account Associated With This Email");
		}
		Customer customer = new Customer();
		customer.setAddress(customerDto.getAddress());
		customer.setName(customerDto.getName());
		customer.setEmail(customerDto.getEmail());
		customer.setPassword(customerDto.getPassword());
		customer.setRole(roleRepository.findByName("CUSTOMER").get());
		customerRepository.save(customer);
	}

}
