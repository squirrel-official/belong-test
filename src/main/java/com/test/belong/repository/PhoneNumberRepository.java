package com.test.belong.repository;

import com.test.belong.entities.PhoneDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneDetails, Long> {
    @Modifying
    @Query("update PhoneDetails set activated = true where phoneNumber=:phoneNumber")
     int activatePhoneNumber(String phoneNumber);
}
