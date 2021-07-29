package com.test.belong.unit.service;

import com.test.belong.entities.Customer;
import com.test.belong.entities.PhoneDetails;
import com.test.belong.exception.CustomerNotFoundException;
import com.test.belong.repository.CustomerRepository;
import com.test.belong.service.impl.CustomerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {
    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;


    @Before
    public void prepare() {
        customerService = new CustomerService(customerRepository);
    }

    @Test
    public void testCustomerPhoneNumbers() {
        Long customerId = 1L;
        Customer customer = new Customer();
        customer.setId(1L);
        List<PhoneDetails> phoneDetails = new ArrayList<>();
        PhoneDetails phoneDetails1 = new PhoneDetails();
        phoneDetails1.setPhoneNumber("+919868825750");
        PhoneDetails phoneDetails2 = new PhoneDetails();
        phoneDetails2.setPhoneNumber("+919868825751");
        phoneDetails.add(phoneDetails1);
        phoneDetails.add(phoneDetails2);
        customer.setPhoneDetails(phoneDetails);

        Optional<Customer> list = Optional.of(customer);

        Mockito.when(customerRepository.findById(customerId)).thenReturn(list);
        List<String> result = customerService.allPhoneNumbers(customerId);

        Assert.assertEquals("+919868825750", result.get(0));
        Assert.assertEquals("+919868825751", result.get(1));
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testWhenCustomerIsNotThere() {
        Long customerId = 1L;
        Optional<Customer> list = Optional.empty();
        Mockito.when(customerRepository.findById(customerId)).thenReturn(list);
        List<String> result = customerService.allPhoneNumbers(customerId);

    }
}
