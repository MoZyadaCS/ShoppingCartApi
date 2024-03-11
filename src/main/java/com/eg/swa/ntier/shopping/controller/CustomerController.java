package com.eg.swa.ntier.shopping.controller;


import com.eg.swa.ntier.shopping.dto.CustomerDto;
import com.eg.swa.ntier.shopping.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CustomerController {

    @Autowired
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @PostMapping("/admin/customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void createCustomerAccount(@RequestBody CustomerDto customerDto){
        customerService.createCustomer(customerDto);
    }
}
