package com.test.belong.unit.controller;

import com.test.belong.controller.CustomerController;
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

@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    CustomerController customerController;

    @Before
    public void prepare() {
        customerController = new CustomerController(customerService);
    }

    @Test
    public void testCustomerPhoneNumbers() {
        Long customerId = 1L;
        List<String> list = new ArrayList<>();
        list.add("+919868825750");
        list.add("+919868825751");
        Mockito.when(customerService.allPhoneNumbers(customerId)).thenReturn(list);
        List<String> result = customerController.phoneNumbers(customerId);

        Assert.assertEquals("+919868825750", result.get(0));
        Assert.assertEquals("+919868825751", result.get(1));
    }


}