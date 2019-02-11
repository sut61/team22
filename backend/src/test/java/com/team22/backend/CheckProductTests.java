package com.team22.backend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.Before;
import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.text.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CheckProductTests {

	@Autowired
    private CheckProductRepository checkproductRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private TypeRepository typeRepository;

	@Autowired
    private TestEntityManager entityManager;

	private Validator validator;
	
	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testCheckCommentize(){
        CheckProduct cp = new CheckProduct();
        cp.setCheckComment("Dr");
         try {
            entityManager.persist(cp);
            entityManager.flush();
            //fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 6);
        }
    }
    @Test
    public void testCheckProductCannotBeNull() {
        CheckProduct cp1 = new CheckProduct();
        cp1.setCheckLevel(null);
        cp1.setCheckComment(null);
        cp1.setCheckDate(null);
        cp1.setCheckTime(null);
        cp1.setChecked(null);
        cp1.setProduct(null);
		   try {
            entityManager.persist(cp1);
            entityManager.flush();
            fail("CheckProduct must not be null to be valid");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 6);
        }
    }
    @Test
    public void testCheckProductComplete() {
        CheckProduct cp2 = new CheckProduct();
        Status sta1 = statusRepository.findByStateId(1L);
        Type type1 = typeRepository.findByTypeIds(1L);
        String cDate = ("01:02:2019");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate Date = LocalDate.parse(cDate,formatter);
        
        String  checkTime   =   ("14:25");
        SimpleDateFormat ft = new SimpleDateFormat ("HH:mm"); 
        Date ti = new Date();
        try {
           ti = ft.parse(checkTime); 
             System.out.println(ti); 
        } catch (ParseException e) { 
         System.out.println("Unparseable using " + ft); 
         }
        Instant instant = Instant.ofEpochMilli(ti.getTime());
        LocalTime time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();
        Product product = new Product();
        product.setProductName("dress");
        product.setProductIds("P45");
        product.setProductPrice(1000);
        product.setProductQuantity(18);
        product.setProductDate(Date);
        product.setStatus(sta1);
        product.setType(type1);
        productRepository.save(product);
        Checking ck = checkingRepository.findByCheckingId(1L);
        cp2.setCheckLevel(55);
        cp2.setCheckComment("pooo");
        cp2.setCheckDate(Date);
        cp2.setCheckTime(time);
        cp2.setChecked(ck);
        cp2.setProduct(product);
		   try {
            entityManager.persist(cp2);
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }
    @Test
    public void tescheckLevelDigit(){
        CheckProduct cp5 = new CheckProduct();
		cp5.setCheckLevel(5555);
         try {
            entityManager.persist(cp5);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(e);
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 6);
        }
    }
    @Test
    public void tescheckLevelDecimalMax(){
        CheckProduct cp6 = new CheckProduct();
		cp6.setCheckLevel(120);
         try {
            entityManager.persist(cp6);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(e);
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 6);
        }
    }
    @Test
    public void tescheckLevelDecimalMin(){
        CheckProduct cp7 = new CheckProduct();
		cp7.setCheckLevel(-20);
         try {
            entityManager.persist(cp7);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(e);
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 6);
        }
    }
    @Test
    public void testCheckingCannotBeNull() {
        Checking ck1 = new Checking();
        ck1.setChecking(null);
		   try {
            entityManager.persist(ck1);
            entityManager.flush();
            fail("Checking must not be null to be valid");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
}