package com.test.belong.controller;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.test.belong.exception.InvalidPhoneNumberException;
import com.test.belong.service.IPhoneNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController("/phone-number")
public class PhoneNumbersController extends AbstractController {

    @Autowired
    private IPhoneNumberService IPhoneNumberService;

    @GetMapping("/all")
    public List<String> phoneNumbers(@RequestParam(name = "offset", defaultValue = "0" ) int pageNumber,
                                     @RequestParam(name = "size", defaultValue = "50" ) int size,
                                     @RequestParam(name = "sorting", defaultValue = "asc") String direction){
        Optional<Sort.Direction> directionOpt = Sort.Direction.fromOptionalString(direction);
        Pageable pageable = defaultPageRequest(pageNumber, size, directionOpt);
        return IPhoneNumberService.getAllPhoneNumbers(pageable);
    }

    @PutMapping("/activate/{phone-number}")
    public void activatePhoneNumber(@PathVariable("phone-number") String phoneNumber){
        try {
            PhoneNumberUtil.getInstance().parse(phoneNumber,
                    Phonenumber.PhoneNumber.CountryCodeSource.UNSPECIFIED.name());
            IPhoneNumberService.activate(phoneNumber);
        }catch (NumberParseException e) {
            throw new InvalidPhoneNumberException("Invalid format/structure of phone number entered.");
        }
    }

}
