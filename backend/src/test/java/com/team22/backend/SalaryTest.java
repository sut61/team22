package com.team22.backend;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import java.util.*;
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
public class SalaryTest {
	@Autowired
	private StaffRepository staffRepository;
	@Autowired
	private SalaryRepository salaryRepository;
	@Autowired
	private PositionRepository positionRepository;
	@Autowired
	private ExperienceRepository experienceRepository;
	@Autowired
	private EducationRepository educationRepository;
	@Autowired
	private PayerRepository payerRepository;
	@Autowired
	private TestEntityManager entityManager;

	private Validator validator;

	@Before
	public void setup() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	// ทดสอบข้อมูลถูกต้อง
	@Test
	public void TestTrueData() {
		Salary sa = new Salary();
		sa.setSalaryId(8L);
		sa.setSalaryIds("SA8");
		sa.setSalaryBankId("B123456789221");
		sa.setSalaryDate(new Date());
		sa.setStaff(staffRepository.findByStaffId(1L));
		sa.setPayer(payerRepository.findByPayerId(1L));
		salaryRepository.save(sa);

		try {
			entityManager.flush();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>>> TestTrueData :");
			System.out.println();
			System.out.println();
			System.out.println();
		} catch (javax.validation.ConstraintViolationException e) {
			fail("Should not pass to this line : TestTrueData");
		}
	}

	// ทดสอบข้อมูล Salary ไม่ null
	@Test
	public void TestSalaryNullError() {

		Salary sa = new Salary();
		sa.setSalaryId(null);
		sa.setSalaryIds(null);
		sa.setSalaryBankId(null);
		sa.setSalaryDate(null);
		sa.setStaff(staffRepository.findByStaffId(null));
		sa.setPayer(payerRepository.findByPayerId(null));

		try {
			entityManager.persist(sa);
			entityManager.flush();
			fail("Should not pass to this line : TestSalaryNullError");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>>> TestSalaryNullError :");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 3);
		}
	}
	
	// Test SalaryBankId Size มากกว่า 2 แต่น้อยกว่า 20
	@Test
	public void TestSalaryBankIdOverMaxSize() {
		Salary sa = new Salary();
		sa.setSalaryId(10L);
		sa.setSalaryIds("SA10");
		sa.setSalaryBankId("B123456789123333333333333");
		sa.setSalaryDate(new Date());
		sa.setStaff(staffRepository.findByStaffId(3L));
		sa.setPayer(payerRepository.findByPayerId(3L));

		salaryRepository.save(sa);
		try {
			entityManager.flush();
			fail("Should not pass to this line : TestSalaryBankIdOverMaxSize");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>>> TestSalaryBankIdOverMaxSize :");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);

		}
	}

	// Test SalaryBankId Pattern ไม่ได้ขึ้นต้นด้วย B
	@Test
	public void TestSalaryBankIdPatternFalse() {
		Salary sa = new Salary();
		sa.setSalaryId(11L);
		sa.setSalaryIds("SA11");
		sa.setSalaryBankId("A234567891111");
		sa.setSalaryDate(new Date());
		sa.setStaff(staffRepository.findByStaffId(2L));
		sa.setPayer(payerRepository.findByPayerId(2L));

		salaryRepository.save(sa);
		try {
			entityManager.flush();
			fail("Should not pass to this line : TestSalaryBankIdPatternFalse");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>>> TestSalaryBankIdPatternFalse :");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}


	// ทดสอบข้อมูล Payer ไม่ null
	@Test
	public void TestPayerNullError() {

		Payer pa = new Payer();
		pa.setPayerId(null);
		pa.setPayerIds(null);
		pa.setPayerName(null);

		try {
			entityManager.persist(pa);
			entityManager.flush();
			fail("Should not pass to this line : TestPayerNullError");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>>> TestSalaryNullError :");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	//Test Payer Pattern PayerName ให้ใส่ไป  word  เริ่มต้น 2 ตัวอักษร
	@Test
	public void TestPayerPattern() {
		Payer p = new Payer();
		p.setPayerId(9L);
		p.setPayerIds("PA9");
		p.setPayerName("123456");

		payerRepository.save(p);
		try {
			entityManager.flush();
			fail("Should not pass to this line : TestPayerPattern");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>>> TestPayerPattern :");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}

	// Test Payer Size มากกว่า 2 แต่น้อยกว่า 20
	@Test
	public void TestPayerOverMaxSize() {
		Payer p = new Payer();
		p.setPayerId(10L);
		p.setPayerIds("PA10");
		p.setPayerName("TungAoooooooooooooooooooooooooooooooooooooooo");

		payerRepository.save(p);
		try {
			entityManager.flush();
			fail("Should not pass to this line : TestPayerSize");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>>> TestPayerSize :");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}

	// Test Payer Unique ข้อมูลห้ามซ้ำ
	@Test
	public void TestPayerUnique() {
		Payer p = new Payer();
		p.setPayerIds("PA5");
		p.setPayerName("Admin5");

		entityManager.persist(p);
		entityManager.flush();

		Payer p1 = new Payer();
		p1.setPayerIds("PA5");
		p1.setPayerName("Admin5");

		try {
			entityManager.persist(p1);
			entityManager.flush();

		} catch (javax.persistence.PersistenceException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println(">>>>> TestPayerUnique :");
			System.out.println(e.getMessage());
			System.out.println();
			System.out.println();

		}
	}

}