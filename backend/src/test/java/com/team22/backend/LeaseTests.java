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
public class LeaseTests {

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
	public void testLeaseNotNull() {
        Lease l1 = new Lease();
        l1.setStatus(null);
        l1.setLeaseStatus(null);
        l1.setDateStart(null);
        l1.setDateEnd(null);
        l1.setCommentRenting(null);
        try {
            entityManager.persist(l1);
            entityManager.flush();
            fail("TestLeaseNotNull Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("1.TestCommentRentingIsNull Error:" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
	
    @Test
    public void testLeaseSuccess(){
        Lease l3 = new Lease();
        String l2Date12 = "07:07:1998";
        String l2Date22 = "06:07:1998";
         DateTimeFormatter l2formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
         LocalDate l2date2 = LocalDate.parse(l2Date12, l2formatter2);
         LocalDate l2date12 = LocalDate.parse(l2Date22, l2formatter2);
        l3.setCommentRenting("ชุดส้วยสวย");
        l3.setStatus("paid");
        l3.setLeaseStatus("Rent");
        l3.setDateStart(l2date2);
        l3.setDateEnd(l2date12);
         try {
            entityManager.persist(l3);
            entityManager.flush();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.TestLeaseSuccess");
			System.out.println();
			System.out.println();
			System.out.println();

            
        } catch(javax.validation.ConstraintViolationException e) {
            fail("TestLeaseSuccess error");
        }
    }
    @Test
    public void testCommentRentingsizeMin(){
        Lease l4 = new Lease();
        String l2Date12 = "07:07:1998";
        String l2Date22 = "06:07:1998";
         DateTimeFormatter l2formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
         LocalDate l2date2 = LocalDate.parse(l2Date12, l2formatter2);
         LocalDate l2date12 = LocalDate.parse(l2Date22, l2formatter2);
         l4.setCommentRenting("ชุด");
         l4.setStatus("paid");
         l4.setLeaseStatus("Rent");
         l4.setDateStart(l2date2);
         l4.setDateEnd(l2date12);

         try {
            entityManager.persist(l4);
            entityManager.flush();

            fail("TestCommentRentingsizeMin Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("3.TestCommentRentingsizeMin Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
   
    @Test
    public void testCommentRentingsizeMax(){
        Lease l4 = new Lease();
        String l2Date12 = "07:07:1998";
        String l2Date22 = "06:07:1998";
         DateTimeFormatter l2formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
         LocalDate l2date2 = LocalDate.parse(l2Date12, l2formatter2);
         LocalDate l2date12 = LocalDate.parse(l2Date22, l2formatter2);
         l4.setCommentRenting("ชุดหกดหกดกฟดหฟเมยเำมเำพเมพเมะพสะืะพะยำเไดบใไฟดใกมออ");
         l4.setStatus("paid");
         l4.setLeaseStatus("Rent");
         l4.setDateStart(l2date2);
         l4.setDateEnd(l2date12);

         try {
            entityManager.persist(l4);
            entityManager.flush();

            fail("TestCommentRentingsizeMax Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("4.TestCommentRentingsizeMax Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }






    @Test
    public void testCommentRentingPatternFasle(){
        Lease l5 = new Lease();
        String l2Date12 = "07:07:1998";
        String l2Date22 = "06:07:1998";
         DateTimeFormatter l2formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
         LocalDate l2date2 = LocalDate.parse(l2Date12, l2formatter2);
         LocalDate l2date12 = LocalDate.parse(l2Date22, l2formatter2);
         l5.setStatus("paid");
         l5.setLeaseStatus("Rent");
         l5.setDateStart(l2date2);
         l5.setDateEnd(l2date12);
         l5.setCommentRenting("ส้วยสวย");
         try {
            entityManager.persist(l5);
            entityManager.flush();

            fail("TestCommentRentingPattern Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("5.TestCommentRentingPattern Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

 @Test
    public void testCommentRentingMustBeUnique() {
        Lease l7 = new Lease();
        String l2Date12 = "07:07:1998";
        String l2Date22 = "06:07:1998";
         DateTimeFormatter l2formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
         LocalDate l2date2 = LocalDate.parse(l2Date12, l2formatter2);
         LocalDate l2date12 = LocalDate.parse(l2Date22, l2formatter2);
         l7.setStatus("paid");
         l7.setLeaseStatus("Rent");
         l7.setDateStart(l2date2);
         l7.setDateEnd(l2date12);
         l7.setCommentRenting("ชุดสวยงาม");
        entityManager.persist(l7);
        entityManager.flush();

        Lease l8 = new Lease();
        l8.setStatus("paid");
        l8.setLeaseStatus("Rent");
        l8.setDateStart(l2date2);
        l8.setDateEnd(l2date12);
        l8.setCommentRenting("ชุดสวยงาม");
        try {
            entityManager.persist(l8);
            entityManager.flush();
            fail("testCommentRentingMustBeUnique Error");
        } catch(javax.persistence.PersistenceException e) {
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("6.TestCommentRentingMustBeUnique Error:" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
        }

        
    }

}
