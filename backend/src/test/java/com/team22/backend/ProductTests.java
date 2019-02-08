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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductTests {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
    private TestEntityManager entityManager;

	private Validator validator;
	
	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testProductIdsComplete() {
        String pDate = ("01:02:2019");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate productDate = LocalDate.parse(pDate,formatter);
        Product p = new Product();
        p.setProductIds("P55");
        p.setProductName("ppppp");
        p.setProductQuantity(5);
        p.setProductPrice(1000);
        p.setProductDate(productDate);
		   try {
            entityManager.persist(p);
            entityManager.flush();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n----------------->> 1.Test Customer Insert DataSuccess \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test Customer Insert DataSuccess Error");
        }
    }
    @Test
    public void testProductCannotBeNull() {
        Product p1 = new Product();
        p1.setProductIds(null);
        p1.setProductName(null);
        p1.setProductQuantity(null);
        p1.setProductPrice(null);
        p1.setProductDate(null);
        try {
            entityManager.persist(p1);
            entityManager.flush();
            fail("Test ProductIds Not Null Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2.1 testProductIdsCannotBeNull\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
    @Test
    public void testProductNemeSizeMax(){
        Product p2 = new Product();
        p2.setProductName("Drqwertyuiopasdfghjklzxcvbnmqwertyuiasdfghjklzxcvbn");
        try {
            entityManager.persist(p2);
            entityManager.flush();
            fail("ProductNeme Size Less Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2.2 testProductNemeSizeMax\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
    @Test
    public void testProductNemeSizeMin(){
        Product p3 = new Product();
        p3.setProductName("Dr");
        try {
            entityManager.persist(p3);
            entityManager.flush();
            fail("ProductNeme Size Less Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2.3 testProductNemeSizeMin\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
	@Test
    public void testProductIdsFirstCharNotP(){
        Product p4 = new Product();
        p4.setProductIds("C1");
        try {
            entityManager.persist(p4);
            entityManager.flush();
            fail("Test ProductIds Firstchar P Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2.4 test ProductIdsFirstCharNotP\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
    @Test
    //(expected=javax.persistence.PersistenceException.class)
    public void testProductIdsMustBeUnique() {
        String pDate = ("01:02:2019");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate productDate = LocalDate.parse(pDate,formatter);
        Product p6 = new Product();
        p6.setProductIds("P55");
        p6.setProductName("ppppp");
        p6.setProductQuantity(5);
        p6.setProductPrice(1000);
        p6.setProductDate(productDate);
        entityManager.persist(p6);
        entityManager.flush();

        Product p5 = new Product();
        p5.setProductIds("P55");
        p5.setProductName("ppppp");
        p5.setProductQuantity(5);
        p5.setProductPrice(1000);
        p5.setProductDate(productDate);
        try{
            entityManager.persist(p5);
            entityManager.flush();
        }catch(javax.persistence.PersistenceException e) {
            System.out.println(); 
            System.out.println();   
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> 2.5 testProductIdsMustBeUnique \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println(); 
            System.out.println(); 
        }
    }
    @Test
    public void testProductIdsNotDigit(){
        Product p7 = new Product();
		p7.setProductIds("Pdefff");
         try {
            entityManager.persist(p7);
            entityManager.flush();
            fail("Should not pass to this line");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println(e);
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
}