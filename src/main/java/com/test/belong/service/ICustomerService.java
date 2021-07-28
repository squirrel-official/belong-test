package com.test.belong.service;

import java.util.List;

public interface ICustomerService {
    List<String> allPhoneNumbers(Long customerId);
}
