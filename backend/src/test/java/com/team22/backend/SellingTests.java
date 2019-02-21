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
public class SellingTests {

	@Autowired
	private SellingRepository sellingRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CustomerRepository customerRepository;

	@Autowired
    private TestEntityManager entityManager;

	private Validator validator;
	
	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testSellingNotNull(){
        Selling s0 = new Selling();
        s0.setCommentSelling("ร้านชุดสวย");
        s0.setStatus("paid");
        String sDate2 = "20:04:1998";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate sdate = LocalDate.parse(sDate2, formatter2);
        s0.setSellingDate(sdate);
        Staff sf = staffRepository.findByStaffId(1L);
        s0.setStaff(sf);
        Customer c = customerRepository.findByCusId(1L);
        s0.setCustomer(c);
        try {
            entityManager.persist(s0);
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n----------------->> 1.Test Selling Insert DataSuccess \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
    public void testSellingNull(){
        Selling s = new Selling();
        s.setCommentSelling(null);
        s.setStatus(null);
        String sDate2 = "20:04:1998";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate sdate = LocalDate.parse(sDate2, formatter2);
        s.setSellingDate(null);
        s.setStaff(null);
        s.setCustomer(null);
        try {
            entityManager.persist(s);
            entityManager.flush();
            fail("Test Selling Not Null Error");

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> SellingNull \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
    @Test
    public void testCommentSellingSizeMin(){
        Selling s1 = new Selling();
        s1.setCommentSelling("ร้านก");
        s1.setStatus("paid");
        String sDate2 = "20:04:1998";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate sdate = LocalDate.parse(sDate2, formatter2);
        s1.setSellingDate(sdate);
        Staff sf1 = staffRepository.findByStaffId(1L);
        s1.setStaff(sf1);
        Customer c1 = customerRepository.findByCusId(1L);
        s1.setCustomer(c1);
        try {
            entityManager.persist(s1);
            entityManager.flush();

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> SellingSizeMin \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
    public void testCommentSellingPattern(){
        Selling s2 = new Selling();
        s2.setCommentSelling("สวยจังงง");
        s2.setStatus("paid");
        String sDate2 = "20:04:1998";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate sdate = LocalDate.parse(sDate2, formatter2);
        s2.setSellingDate(sdate);
        Staff sf2 = staffRepository.findByStaffId(1L);
        s2.setStaff(sf2);
        Customer c2 = customerRepository.findByCusId(1L);
        s2.setCustomer(c2);
        try {
            entityManager.persist(s2);
            entityManager.flush();
            fail("Test Selling Pattern Error");

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> SellingPattern \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testReviewCommentUnique() {
        Selling s4 = new Selling();
        s4.setCommentSelling("ร้านชุดสวยจัง");
        s4.setStatus("not paid");
        String sDate2 = "20:04:1998";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate sdate = LocalDate.parse(sDate2, formatter2);
        s4.setSellingDate(sdate);
        Staff sf4 = staffRepository.findByStaffId(1L);
        s4.setStaff(sf4);
        Customer c4 = customerRepository.findByCusId(1L);
        s4.setCustomer(c4);
        entityManager.persist(s4);
        entityManager.flush();

        Selling s5 = new Selling();
        s5.setCommentSelling("ร้านชุดสวยจัง");
        s5.setStatus("not paid");
        s5.setSellingDate(sdate);
        Staff sf5 = staffRepository.findByStaffId(1L);
        s5.setStaff(sf5);
        Customer c5 = customerRepository.findByCusId(1L);
        s5.setCustomer(c5);
        try {
            entityManager.persist(s5);
            entityManager.flush();
            fail("Test Selling CommentUnique Error");
        } catch(javax.persistence.PersistenceException e) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n" + e + "----------------->> Unique \n\n\n\n\n");;
            System.out.println();
            System.out.println();
            System.out.println();
        }


    }
    @Test
    public void testCommentSellingSizeMax(){
        Selling s6 = new Selling();
        s6.setCommentSelling("ร้านกกกกกกกกกกกดเ้่าสวหกกกกกกกกกกกกกดดดดดด");
        s6.setStatus("paid");
        String sDate2 = "20:04:1998";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate sdate = LocalDate.parse(sDate2, formatter2);
        s6.setSellingDate(sdate);
        Staff sf5 = staffRepository.findByStaffId(1L);
        s6.setStaff(sf5);
        Customer c6 = customerRepository.findByCusId(1L);
        s6.setCustomer(c6);
        try {
            entityManager.persist(s6);
            entityManager.flush();
            fail("Test Selling SizeMax Error");

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> SellingSizeMax \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
 }

