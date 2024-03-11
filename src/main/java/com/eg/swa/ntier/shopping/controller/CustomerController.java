package com.eg.swa.ntier.shopping.controller;


import com.eg.swa.ntier.shopping.dto.CustomerDto;
import com.eg.swa.ntier.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController("/api/customers/")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerAccount(@RequestBody CustomerDto customerDto){
        customerService.createCustomer(customerDto);
    }
}
