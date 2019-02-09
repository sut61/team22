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
    private CustomerRepository customerRepository;

    @Autowired
    private SellingRepository sellingRepository;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private LeaseRepository leaseRepository;
    
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
        PayMent paymentdb1 = new PayMent();
        Date paydate = new Date();
        Customer c1 = customerRepository.findByCusId(1L);
        Selling s1 = sellingRepository.findBySellingId(1L);
        Booking b1 = bookingRepository.findByBookingId(1L);
        Lease l1 = leaseRepository.findByLeaseId(1L);
        paymentdb1.setBillPayment("BillPayment2");
        paymentdb1.setTypePay("Renting");
        paymentdb1.setStatusPay("paid");
        paymentdb1.setDatePay(paydate);
        paymentdb1.setCustomer(c1);
        paymentdb1.setSelling(s1);
        paymentdb1.setBooking(b1);
        paymentdb1.setLease(l1);
        
        try {
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n----------------->> 1.Test PayMent Insert DataSuccess \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            entityManager.persist(paymentdb1);
            entityManager.flush();
            
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test PayMent Insert DataSuccess Error");
        }
    }

    @Test
	public void testTypePaySize() {
        PayMent paymentdb2 = new PayMent();
        Date paydate = new Date();
        Customer c2 = customerRepository.findByCusId(2L);
        Selling s1 = sellingRepository.findBySellingId(1L);
        Booking b1 = bookingRepository.findByBookingId(1L);
        Lease l1 = leaseRepository.findByLeaseId(1L);
        paymentdb2.setBillPayment("BillPayment3");
        paymentdb2.setTypePay("Sellingggggg");
        paymentdb2.setStatusPay("paid");
        paymentdb2.setDatePay(paydate);
        paymentdb2.setCustomer(c2);
        paymentdb2.setSelling(s1);
        paymentdb2.setBooking(b1);
        paymentdb2.setLease(l1);
        try {
            entityManager.persist(paymentdb2);
            entityManager.flush();
            fail("TypePay Size Not Equal 7 ");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2 .TypePay Size Equal 7 Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
	public void testPaymentNotNull() {
        PayMent paymentdb3 = new PayMent();
        paymentdb3.setPmId(null);
        paymentdb3.setBillPayment(null);
        paymentdb3.setTypePay(null);
        paymentdb3.setStatusPay(null);
        paymentdb3.setDatePay(null);
        paymentdb3.setCustomer(null);
        paymentdb3.setSelling(null);
        paymentdb3.setBooking(null);
        paymentdb3.setLease(null);
        try {
            entityManager.persist(paymentdb3);
            entityManager.flush();
            fail("Test Payment Not Null ");
            
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 3.Test Payment Not Null Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
    @Test
	public void testPaymentFirstRSB() {
        PayMent paymentdb4 = new PayMent();
        Date paydate = new Date();
        Customer c3 = customerRepository.findByCusId(3L);
        Selling s1 = sellingRepository.findBySellingId(1L);
        Booking b1 = bookingRepository.findByBookingId(1L);
        Lease l1 = leaseRepository.findByLeaseId(1L);
        paymentdb4.setBillPayment("BillPayment4");
        paymentdb4.setTypePay("Aooking");
        paymentdb4.setStatusPay("paid");
        paymentdb4.setDatePay(paydate);
        paymentdb4.setCustomer(c3);
        paymentdb4.setSelling(s1);
        paymentdb4.setBooking(b1);
        paymentdb4.setLease(l1);
        try {
            entityManager.persist(paymentdb4);
            entityManager.flush();
            fail("Test Payment First RSB ");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 4.Test Payment First RSB Error \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
    public void TestUniqeBillPaymentError() {
        PayMent paymentdb5 = new PayMent();
        Date paydate = new Date();
        Customer c5 = customerRepository.findByCusId(5L);
        Selling s1 = sellingRepository.findBySellingId(1L);
        Booking b1 = bookingRepository.findByBookingId(1L);
        Lease l1 = leaseRepository.findByLeaseId(1L);
        paymentdb5.setBillPayment("BillPayment5");
        paymentdb5.setTypePay("Booking");
        paymentdb5.setStatusPay("paid");
        paymentdb5.setDatePay(paydate);
        paymentdb5.setCustomer(c5);
        paymentdb5.setSelling(s1);
        paymentdb5.setBooking(b1);
        paymentdb5.setLease(l1);
        entityManager.persist(paymentdb5);
        entityManager.flush();

        PayMent paymentdb6 = new PayMent();
        Date paydate2 = new Date();
        Customer c4 = customerRepository.findByCusId(4L);
        paymentdb6.setBillPayment("BillPayment5");
        paymentdb6.setTypePay("Selling");
        paymentdb6.setStatusPay("paid");
        paymentdb6.setDatePay(paydate2);
        paymentdb6.setCustomer(c4);
        paymentdb6.setSelling(s1);
        paymentdb6.setBooking(b1);
        paymentdb6.setLease(l1);
        try {
            entityManager.persist(paymentdb6);
            entityManager.flush();
        } catch (javax.persistence.PersistenceException e) {
            System.out.println();
            System.out.println();
            System.out
                    .println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 5.UniqeBillPayment \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
        }
    }
}