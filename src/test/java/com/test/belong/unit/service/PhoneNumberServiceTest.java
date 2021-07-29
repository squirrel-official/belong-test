package com.test.belong.unit.service;

import com.test.belong.exception.InvalidPhoneNumberException;
import com.test.belong.repository.PhoneNumberRepository;
import com.test.belong.service.impl.PhoneNumberService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;

@RunWith(MockitoJUnitRunner.class)
public class PhoneNumberServiceTest {
    @Mock
    PhoneNumberRepository phoneNumberRepository;

    PhoneNumberService phoneNumberService;


    @Before
    public void prepare() {
        phoneNumberService = new PhoneNumberService(phoneNumberRepository);
    }

    @Test
    public void activatePhoneNumbers() {
        String phoneNumber = "+919868825750";
        Mockito.when(phoneNumberRepository.activatePhoneNumber(phoneNumber)).thenReturn(1);
        phoneNumberService.activate(phoneNumber);
        Mockito.verify(phoneNumberRepository, times(1)).activatePhoneNumber(phoneNumber);

    }

    @Test(expected = InvalidPhoneNumberException.class)
    public void activateNonExistentPhoneNumber() {
        String phoneNumber = "+000000000";
        Mockito.when(phoneNumberRepository.activatePhoneNumber(phoneNumber)).thenReturn(0);
        phoneNumberService.activate(phoneNumber);

    }
}
