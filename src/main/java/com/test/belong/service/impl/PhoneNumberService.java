package com.test.belong.service.impl;

import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.test.belong.entities.PhoneDetails;
import com.test.belong.exception.InvalidPhoneNumberException;
import com.test.belong.repository.PhoneNumberRepository;
import com.test.belong.service.IPhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PhoneNumberService implements IPhoneNumberService {
    private final PhoneNumberRepository phoneNumberRepository;

    @Override
    public List<String> getAllPhoneNumbers(Pageable pageable) {
        Page<PhoneDetails> result = phoneNumberRepository.findAll(pageable);
        return result.stream().map(r -> r.getPhoneNumber()).collect(Collectors.toList());
    }

    @Override
    public void activate(String phoneNumber) {
        int result = phoneNumberRepository.activatePhoneNumber(phoneNumber);
        if (result == 0) {
            throw new InvalidPhoneNumberException("The phone number does not exist");
        }
    }

    @Autowired
    public PhoneNumberService(PhoneNumberRepository phoneNumberRepository) {
        this.phoneNumberRepository = phoneNumberRepository;
    }
}
