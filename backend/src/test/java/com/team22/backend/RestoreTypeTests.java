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
@DataJpaTest
public class RestoreTypeTests {
    @Autowired
    private RestoreTypeRepository restoreTypeRepository;
    @Autowired
    private TestEntityManager entityManager;

	private Validator validator;
	

	@Before
    public void setup() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testRestoreTypeIsNull(){
            RestoreType rtype = new RestoreType();
            rtype.setTypeRestoreName(null);
            try {
                entityManager.persist(rtype);
                entityManager.flush();
                fail("TestRestoreTypeIsNull Error");
            } catch(javax.validation.ConstraintViolationException e) {
                Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
                System.out.println();
                System.out.println();
                System.out.println();
                System.out.println("1.TestRestoreTypeIsNull :" + e +"\n");
                System.out.println();
                System.out.println();
                System.out.println();
                assertEquals(violations.isEmpty(), false);
                assertEquals(violations.size(), 1);
            }
        }
    @Test
    public void testRestoreTypeSuccess(){
        RestoreType rtype = new RestoreType();
        rtype.setTypeRestoreName("sdsafsg");

        try {
            entityManager.persist(rtype);
            entityManager.flush();
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println("1.TestRestoreTypeIsNull");
            System.out.println();
            System.out.println();
            System.out.println();
    
        } catch(javax.validation.ConstraintViolationException e) {
            fail("Test RestoreTypeSuccces Fail");
        }
    }
}