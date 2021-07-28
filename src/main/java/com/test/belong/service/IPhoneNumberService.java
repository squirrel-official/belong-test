package com.test.belong.service;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPhoneNumberService {
    List<String> getAllPhoneNumbers(Pageable pageable);

    void activate(String phoneNumber);
}
