package com.team22.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.OptionalInt;
import java.util.Set;
import java.util.Date;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.Before;
import org.junit.Test;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PaymentTest {

	@Autowired
	private PayMentRepository payMentRepository;

    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;
	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
	}
	
    @Test
	public void testPayMentInsertDataSuccess() {
        PayMent paymentdb = new PayMent();
        paymentdb.setTypePay("Renting");
        paymentdb.setStatusPay("paid");
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test PayMent Insert DataSuccess Error");
        }
    }
    
	
    @Test
	public void testTypePaySizeLessThan() {
        PayMent paymentdb = new PayMent();
        paymentdb.setTypePay("Selling");
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
            fail("TypePay Size Less Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
	public void testPaymentNotNull() {
        PayMent paymentdb = new PayMent();
        paymentdb.setPmId(null);
        paymentdb.setTypePay(null);
        paymentdb.setStatusPay(null);
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
            fail("Test Payment Not Null Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
	public void testPaymentFirstRSB() {
        PayMent paymentdb = new PayMent();
        paymentdb.setTypePay("Booking");
        try {
            entityManager.persist(paymentdb);
            entityManager.flush();
            fail("Test CustomerPhone First RSB Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

}

