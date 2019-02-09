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
public class BookingCancleTest{

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
	public void testBookingInsertDataSuccess() {
        BookingCancle bc1 = new BookingCancle();
        Date bcDate1 = new Date(); 
        bc1.setBookingCancleIDs("Bc3");
        bc1.setBookingCancleDate(bcDate1);
        bc1.setBookingCancleStatus("Booking");
        bc1.setBookingCancleReason("เพราะไก่อ่อน");
        bc1.setBooking(bookingRepository.findByBookingId(1L));
        bc1.setTypeReason(typeReasonRepository.findByTypeReasonID(1L));
        
        try {
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n----------------->> 1.Test Booking Insert DataSuccess \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            entityManager.persist(bc1);
            entityManager.flush();
            
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test Booking Insert DataSuccess Error");
        }
    } 
	@Test
	public void testbookingCancleCannotBeNull() {
        BookingCancle bc2 = new BookingCancle();
        bc2.setBooking(null);
        bc2.setBookingCancleID(null);
        bc2.setBookingCancleIDs(null);
        bc2.setBookingCancleDate(null);
        bc2.setBookingCancleStatus(null);
        bc2.setBookingCancleReason(null);
        bc2.setTypeReason(null);

		try {
            entityManager.persist(bc2);
            entityManager.flush();
            fail("Test BookingCancleReason Cannot Be Null");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 2 -------->> 2. Test BookingCancle  Cannot Be Null ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 6);
			
        }
    }

    @Test
	public void testbookingCancleReasonSizeMIN() {
		BookingCancle bc3 = new BookingCancle();
        Date bcDate3 = new Date(); 
        Booking b1 = bookingRepository.findByBookingId(1L);
        bc3.setBookingCancleIDs("Bc1");
        bc3.setBookingCancleDate(bcDate3);
        bc3.setBookingCancleStatus("Cancled");
        bc3.setBookingCancleReason("เพราะล");
        bc3.setBooking(b1);
        bc3.setTypeReason(typeReasonRepository.findByTypeReasonID(1L));

		try {
            entityManager.persist(bc3);
            entityManager.flush();
            fail("Test BookingCancleReason Size MIN");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 2 -------->> 3. Test BookingCancleReason Size Min ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
        }
	}
    @Test
	public void testbookingCancleReasonSizeMAX() {
		BookingCancle bc4 = new BookingCancle();
        Date bcDate4 = new Date(); 
        Booking b1 = bookingRepository.findByBookingId(1L);
        bc4.setBookingCancleIDs("Bc1");
        bc4.setBookingCancleDate(bcDate4);
        bc4.setBookingCancleStatus("Cancled");
        bc4.setBookingCancleReason("เพราะffjwfwjjfeifiefjiefjiefjgrrgrgrgrgedfsaafsas");
        bc4.setBooking(b1);
        bc4.setTypeReason(typeReasonRepository.findByTypeReasonID(1L));
		try {
            entityManager.persist(bc4);
            entityManager.flush();
            fail("Test BookingCancleReason Size MAX");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 2 -------->> 4. Test BookingCancleReason Size Max ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);
        }
	}
	
	@Test
	public void TestbookingCancleReasonCannotBePatten() {
        BookingCancle bc5 = new BookingCancle();
        Date bcDate5 = new Date(); 
        Booking b1 = bookingRepository.findByBookingId(1L);
        bc5.setBookingCancleIDs("Bc1");
        bc5.setBookingCancleDate(bcDate5);
        bc5.setBookingCancleStatus("Cancled");
        bc5.setBookingCancleReason("กกดก");
        bc5.setBooking(b1);
        bc5.setTypeReason(typeReasonRepository.findByTypeReasonID(1L));

		try {
            entityManager.persist(bc5);
            entityManager.flush();
            fail("Test BookingCancleReason Cannot Be Pattern");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 2 -------->> 5. Test BookingCancleReason Cannot Be Pattern ");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
			//e.printStackTrace();
        }
    }
    

    @Test
    public void TestUniqeBookingCancleReasonError() {
        BookingCancle bc1 = new BookingCancle();
        Date bcDate1 = new Date(); 
        bc1.setBookingCancleIDs("Bc4");
        bc1.setBookingCancleDate(bcDate1);
        bc1.setBookingCancleStatus("Booking");
        bc1.setBookingCancleReason("เพราะไก่อ่อน");
        bc1.setBooking(bookingRepository.findByBookingId(1L));
        bc1.setTypeReason(typeReasonRepository.findByTypeReasonID(1L));
        
		entityManager.persist(bc1);
		entityManager.flush();
		
        BookingCancle bc2 = new BookingCancle();
        Date bcDate2 = new Date(); 
        bc2.setBookingCancleIDs("Bc4");
        bc2.setBookingCancleDate(bcDate2);
        bc2.setBookingCancleStatus("Booking");
        bc2.setBookingCancleReason("เพราะไก่อ่อน");
        bc2.setBooking(bookingRepository.findByBookingId(1L));
        bc2.setTypeReason(typeReasonRepository.findByTypeReasonID(1L));
        
    try { 
        entityManager.persist(bc2);
        entityManager.flush();
        
    } catch(javax.persistence.PersistenceException e) {
        System.out.println();
            System.out.println();
            System.out.println(e+"  ---------Sprint 2 -------->> 6. Test Uniqe BookingCancleReason Error ");
            System.out.println();
            System.out.println();
    }
}  
}