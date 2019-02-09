package com.team22.backend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;
import java.util.*; 
import javax.validation.*;
import static org.junit.Assert.fail;
import org.junit.*;
import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class BookingTest{

	@Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private BookingCancleRepository bookingCancleRepository;

    @Autowired
    private TypeReasonRepository typeReasonRepository;
    
    @Autowired
    private TestEntityManager entityManager;

    private Validator validator;

    @Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testBookingNull() {
        Booking b1 = new Booking();
        b1.setBookingId(null);
        b1.setBookingDate(null);
        b1.setCategory(null);
        b1.setStatus(null);
        b1.setStatusBooking(null);
        b1.setStyle(null);
        try {
            entityManager.persist(b1);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 1 -------->> 1. Test Booking Null ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 4);
        }
    }

    @Test
    public void testCategoryPattern(){
        Booking b2 = new Booking();
        String BDate1 = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate bdate1 = LocalDate.parse(BDate1, formatter1); 
        b2.setBookingDate(bdate1);
        b2.setCategory("กกกกกกก");
        b2.setStatus("not paid");
        b2.setStatusBooking("Booking");
        
        try {
            entityManager.persist(b2);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 1 -------->> 2. Test Category Pattern ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testCategorySize(){
        Booking b3 = new Booking();
        String BDate2 = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate bdate2 = LocalDate.parse(BDate2, formatter1); 
        b3.setBookingDate(bdate2);
        b3.setCategory("งาน");
        b3.setStatus("not paid");
        b3.setStatusBooking("Booking");
        try {
            entityManager.persist(b3);
            entityManager.flush();

            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 1 -------->> 3. Test Category Size ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
    public void testCategoryTrue(){
        Booking b4 = new Booking();
        String BDate = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate bdate = LocalDate.parse(BDate, formatter1); 
        b4.setBookingDate(bdate);
        b4.setCategory("งานวันเด็ก");
        b4.setStatus("not paid");
        b4.setStatusBooking("Booking");
        try {
            entityManager.persist(b4);
            entityManager.flush();

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 1 -------->> 4. Test Booking True ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 3);
        }
    }

    @Test
    public void TestUniqeCategoryError() {
        Booking b5 = new Booking();
        String BDate = "20:04:1998";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate bdate = LocalDate.parse(BDate, formatter); 
        b5.setBookingDate(bdate);
        b5.setCategory("งานวันเด็ก");
        b5.setStatus("not paid");
        b5.setStatusBooking("Booking");

        Booking b6 = new Booking();
        String BDate1 = "20:04:1998";
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate bdate1 = LocalDate.parse(BDate1, formatter2); 
        b6.setBookingDate(bdate1);
        b6.setCategory("งานวันเด็ก");
        b6.setStatus("not paid");
        b6.setStatusBooking("Booking");
		
    try { 
        entityManager.persist(b6);
        entityManager.flush();
        
    } catch(javax.persistence.PersistenceException e) {
        System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 1 -------->> 5. Test Uniqe Category Error ");
            System.out.println();
            System.out.println();
    }
}

    // ----------------------------------------------------------------------------------------------
    
}