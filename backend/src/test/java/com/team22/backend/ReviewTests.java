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
@SpringBootTest
@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewTests {

	@Autowired
	private ReviewRepository reviewRepository;

    @Autowired
    private SellingRepository sellingRepository;

    @Autowired
    private LevelReviewRepository levelReviewRepository;

    @Autowired
    private TestEntityManager entityManager;

	private Validator validator;
	
	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    @Test
    public void testReviewNotNull(){
        Review r = new Review();
        Selling s = sellingRepository.findBySellingId(1L);
        r.setReviewComment("ควรมีชุดมากกว่านี้");
        r.setMent2("ชุดเยอะมาก");
        String RDate1 = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate rdate = LocalDate.parse(RDate1, formatter1);
        r.setReviewDate(rdate);
        r.setSelling(s);
        LevelReview lr = levelReviewRepository.findByLevelReviewId(1L);
        r.setLevelReview(lr);
        try {
            entityManager.persist(r);
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n----------->> 1.Test Review Insert DataSuccess \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
    public void testReviewNull(){
        Review r1 = new Review();
        r1.setReviewComment(null);
        r1.setMent2(null);
        r1.setReviewDate(null);
        r1.setSelling(null);
        r1.setLevelReview(null);
        try {
            entityManager.persist(r1);
            entityManager.flush();
            fail("Test Review Not Null Error");

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> ReviewNull \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }
    @Test
    public void testReviewCommenSizeMin(){
        Review r2 = new Review();
        Selling s2 = sellingRepository.findBySellingId(1L);
        r2.setReviewComment("ควรก");
        r2.setMent2("ชุดเยอะมาก");
        String RDate1 = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate rdate = LocalDate.parse(RDate1, formatter1);
        r2.setReviewDate(rdate);
        r2.setSelling(s2);
        LevelReview lr1 = levelReviewRepository.findByLevelReviewId(1L);
        r2.setLevelReview(lr1);
        try {
            entityManager.persist(r2);
            entityManager.flush();
            fail("Test Review CommenSizeMin Error");

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> ReviewSizeMin \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
    public void testReviewCommenSizeMax(){
        Review r3 = new Review();
        Selling s3 = sellingRepository.findBySellingId(1L);
        r3.setReviewComment("ควร12345678901234567890");
        r3.setMent2("ชุดเยอะมาก");
        String RDate1 = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate rdate = LocalDate.parse(RDate1, formatter1);
        r3.setReviewDate(rdate);
        r3.setSelling(s3);
         LevelReview lr3 = levelReviewRepository.findByLevelReviewId(1L);
        r3.setLevelReview(lr3);
        try {
            entityManager.persist(r3);
            entityManager.flush();
            fail("Test Review CommenSizeMax Error");

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> ReviewSize \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
    public void testReviewCommentPattern(){
        Review r4 = new Review();
        Selling s4 = sellingRepository.findBySellingId(1L);
        r4.setReviewComment("สวยจังงง");
        r4.setMent2("ชุดเยอะมาก");
        String RDate1 = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate rdate = LocalDate.parse(RDate1, formatter1);
        r4.setReviewDate(rdate);
        r4.setSelling(s4);
        LevelReview lr4 = levelReviewRepository.findByLevelReviewId(1L);
        r4.setLevelReview(lr4);
        try {
            entityManager.persist(r4);
            entityManager.flush();
            fail("Test Review CommentPattern Error");

        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n\n\n\n\n\n" + e + "----------------->> ReviewRattern \n\n\n\n\n\n\n\n\n\n\n");
            System.out.println();
            System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }

    @Test
    public void testReviewCommentUnique() {
        Review r8 = new Review();
        Selling s7 = sellingRepository.findBySellingId(1L);
        r8.setReviewComment("ควรซื้อเสื้อผ้า");
        r8.setMent2("ชุดเยอะมาก");
        String RDate1 = "20:04:1998";
        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate rdate = LocalDate.parse(RDate1, formatter1);
        r8.setReviewDate(rdate);
        r8.setSelling(s7);
        LevelReview lr8 = levelReviewRepository.findByLevelReviewId(1L);
        r8.setLevelReview(lr8);
        entityManager.persist(r8);
        entityManager.flush();

        Review r9 = new Review();
        Selling s8 = sellingRepository.findBySellingId(1L);
        r9.setReviewComment("ควรซื้อเสื้อผ้า");
        r9.setMent2("ชุดเยอะมาก");
        r9.setReviewDate(rdate);
        r9.setSelling(s8);
        LevelReview lr9 = levelReviewRepository.findByLevelReviewId(1L);
        r9.setLevelReview(lr9);

        try {
            entityManager.persist(r9);
            entityManager.flush();
            fail("Test Review CommentUnique Error");

        } catch(javax.persistence.PersistenceException e) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("\n\n\n\n" + e + "----------------->> ReviewUnique \n\n\n\n\n");;
            System.out.println();
            System.out.println();
            System.out.println();
        }
    }


}