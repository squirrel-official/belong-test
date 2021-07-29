package com.test.belong.unit.controller;

import com.test.belong.controller.PhoneNumbersController;
import com.test.belong.exception.InvalidPhoneNumberException;
import com.test.belong.service.impl.PhoneNumberService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class PhoneNumberControllerTest {

    @Mock
    PhoneNumberService phoneNumberService;

    PhoneNumbersController phoneNumbersController;

    @Before
    public void prepare() {
        phoneNumbersController = new PhoneNumbersController(phoneNumberService);
    }

    @Test
    public void testAllPhoneNumbers() {
        List<String> list = new ArrayList<>();
        list.add("+919868825750");
        list.add("+919868825751");
        list.add("");
        Mockito.when(phoneNumberService.getAllPhoneNumbers(any())).thenReturn(list);
        List<String> result = phoneNumbersController.phoneNumbers(0, 50, "asc");
        Assert.assertEquals("+919868825750", result.get(0));
        Assert.assertEquals("+919868825751", result.get(1));
    }

    @Test
    public void testActivate() {
        String phoneNumber = "+919868825750";
        phoneNumbersController.activatePhoneNumber(phoneNumber);
        Mockito.verify(phoneNumberService, times(1)).activate(phoneNumber);
    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void testActivateError() {
        String phoneNumber = "919868825750"; // not in international format
        phoneNumbersController.activatePhoneNumber(phoneNumber);
    }
}