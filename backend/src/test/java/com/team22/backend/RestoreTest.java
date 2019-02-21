package com.team22.backend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.Collections;
import java.util.Date;
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
@SpringBootTest
@DataJpaTest
public class RestoreTest {

    @Autowired
    private RestoreTypeRepository restoreTypeRepository;
	@Autowired
    private RestoreRepository restoreRepository;
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
    public void testRestoreIsNull(){
        Restore r1 = new Restore();
        r1.setRestoreType(null);
        r1.setDateRestore(null);
        r1.setStatusRestore(null);
        r1.setLease(null);
        r1.setCommentRestore(null);
       
        try {
            entityManager.persist(r1);
            entityManager.flush();
            fail("Test commentRestore Not Null Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("1.Test commentRestore Not Null Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println();
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 5);
        }
    }

    @Test
    public void testRestoreSuccess(){
        Restore r2 = new Restore();
        Date restoreDate = new Date();
        Lease lease1= leaseRepository.findByLeaseId(1L);
        RestoreType RT1 = restoreTypeRepository.findByRestoreTypeId(1L);
        r2.setStatusRestore("restore");
        r2.setCommentRestore("ชุดมีรอยเปื้อน");
        r2.setDateRestore(restoreDate);
        r2.setLease(lease1);
        r2.setRestoreType(RT1);
        try {
            entityManager.persist(r2);
            entityManager.flush();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2 Test RestoreSuccces True ");
			System.out.println();
			System.out.println();
			System.out.println();
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test RestoreSuccces Fail");
            
        }
    }
    @Test
    public void testCommentRestoreSizeMin(){
        Restore r3 = new Restore();
        Date restoreDate = new Date();
        Lease lease2 = leaseRepository.findByLeaseId(1L);
        RestoreType RT1 = restoreTypeRepository.findByRestoreTypeId(1L);
        r3.setCommentRestore("ชุด");
        r3.setStatusRestore("restore");
        r3.setDateRestore(restoreDate);
        r3.setLease(lease2);
        r3.setRestoreType(RT1);
        try {
            entityManager.persist(r3);
            entityManager.flush();
            fail("CommentRestore Size Less Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("3.Test CommentRestore Size Less Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 2);
        }
    }

    @Test
    public void testCommentRestoreSizeMax(){
        Restore r4 = new Restore();
        Date restoreDate = new Date();
        Lease lease3 = leaseRepository.findByLeaseId(1L);
        RestoreType RT1 = restoreTypeRepository.findByRestoreTypeId(1L);
        r4.setCommentRestore("ชุดากาดกยดนทอดำานทดนยดทไยนดฟทหฟากทาหนทานทแากนทดกานทอนากทอ");
        r4.setStatusRestore("restore");
        r4.setDateRestore(restoreDate);
        r4.setLease(lease3);
        r4.setRestoreType(RT1);
        try {
            entityManager.persist(r4);
            entityManager.flush();
            fail("CommentRestore Size Max Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("4.Test CommentRestore Size Max Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }
    @Test
    public void testCommentRestorePatternFasle(){
        Restore r5 = new Restore();
        Date restoreDate = new Date();
        Lease lease4 = leaseRepository.findByLeaseId(1L);
        RestoreType RT1 = restoreTypeRepository.findByRestoreTypeId(1L);
        r5.setCommentRestore("มีรอยตำหนิ");
        r5.setStatusRestore("restore");
        r5.setDateRestore(restoreDate);
        r5.setLease(lease4);
        r5.setRestoreType(RT1);
        try {
            entityManager.persist(r5);
            entityManager.flush();
            fail("CommentRestore Pattern Error");
        } catch(javax.validation.ConstraintViolationException e) {
            Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("5.Test CommentRestore Pattern Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
            assertEquals(violations.isEmpty(), false);
            assertEquals(violations.size(), 1);
        }
    }


    @Test
    public void testCommentRestoreMustBeUnique() {
        Restore r6 = new Restore();
        Date restoreDate1 = new Date();
        Lease lease5 = leaseRepository.findByLeaseId(1L);
        RestoreType RT1 = restoreTypeRepository.findByRestoreTypeId(1L);
        r6.setCommentRestore("ชุดมีรอยตำหนิ");
        r6.setDateRestore(restoreDate1);
        r6.setStatusRestore("restore");
        r6.setLease(lease5);
        r6.setRestoreType(RT1);
        entityManager.persist(r6);
        entityManager.flush();

        Restore r7 = new Restore();
        Date restoreDate2 = new Date();
        Lease lease6 = leaseRepository.findByLeaseId(1L);
        RestoreType RT2 = restoreTypeRepository.findByRestoreTypeId(1L);
        r7.setCommentRestore("ชุดมีรอยตำหนิ");
        r7.setDateRestore(restoreDate2);
        r7.setStatusRestore("restore");
        r7.setLease(lease6);
        r7.setRestoreType(RT2);
        
        try {
            entityManager.persist(r7);
            entityManager.flush();
            fail("CommentRestore MustBeUnique Error");
        } catch(javax.persistence.PersistenceException e) {
            System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("6.Test CommentRestore MustBeUnique Error :" + e +"\n");
			System.out.println();
			System.out.println();
			System.out.println(); 
        }

        
    }

}