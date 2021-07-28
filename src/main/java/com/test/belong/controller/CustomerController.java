package com.test.belong.controller;

import com.test.belong.service.impl.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/user")
public class CustomerController extends AbstractController{

    private CustomerService customerService;

    @GetMapping("{customer-id}/phone-numbers")
    public List<String> phoneNumbers(@PathVariable("customer-id") Long customerId){
        return customerService.allPhoneNumbers(customerId);
    }

    @Autowired
    public CustomerController(CustomerService customerService){
        this.customerService =customerService;
    }

}
