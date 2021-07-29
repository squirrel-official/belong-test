package com.test.belong.service;

import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IPhoneNumberService {
    List<String> getAllPhoneNumbers(Pageable pageable);

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE)
    void activate(String phoneNumber);
}
