package com.test.belong.service.impl;

import com.test.belong.entities.Customer;
import com.test.belong.entities.PhoneDetails;
import com.test.belong.exception.CustomerNotFoundException;
import com.test.belong.repository.CustomerRepository;
import com.test.belong.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<String> allPhoneNumbers(Long customerId) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if(customerOptional.isPresent()){
            Customer customer = customerOptional.get();
            List<PhoneDetails> allPhoneDetails = customer.getPhoneDetails();
            return allPhoneDetails.stream().map(r->r.getPhoneNumber()).collect(Collectors.toList());

        }else {
            throw new CustomerNotFoundException("The user is not found.");
        }
    }
}
