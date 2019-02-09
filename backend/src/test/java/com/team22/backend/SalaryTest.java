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
		sa.setSalaryId(9L);
		sa.setSalaryIds("SA9");
		sa.setSalaryDate(new Date());
		sa.setStaff(staffRepository.findByStaffId(1L));
		sa.setPayer(payerRepository.findByPayerId(1L));
		salaryRepository.save(sa);

		try {
			entityManager.flush();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("1.1 > TestTrueData :");
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
		sa.setSalaryDate(null);
		sa.setStaff(staffRepository.findByStaffId(null));
		sa.setPayer(payerRepository.findByPayerId(null));

		// salaryRepository.save(sa);
		try {
			entityManager.persist(sa);
			entityManager.flush();
			fail("Should not pass to this line : TestSalaryNullError");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.1 > TestSalaryNullError :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	// ทดสอบข้อมูล Salary ไม่ null
	@Test
	public void TestPayerNullError() {

		Payer pa = new Payer();
		pa.setPayerId(null);
		pa.setPayerIds(null);
		pa.setPayerName(null);

		// payerRepository.save(pa);
		try {
			entityManager.persist(pa);
			entityManager.flush();
			fail("Should not pass to this line : TestPayerNullError");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.1 > TestSalaryNullError :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	// ทดสอบ size phone ผิด
	@Test
	public void TestSizeStaffPhoneError() {

		Salary sa = new Salary();
		sa.setSalaryId(1L);
		sa.setSalaryIds("SA1");
		sa.setSalaryDate(sa.getSalaryDate());
		sa.setStaff(staffRepository.findByStaffId(1L));
		sa.setStaff(staffRepository.findByStaffPhone("086-141-98333")); // Testing
		sa.setStaff(staffRepository.findByStaffSalary(50000));
		sa.setPayer(payerRepository.findByPayerId(1L));

		salaryRepository.save(sa);
		try {
			// entityManager.persist(sa);
			entityManager.flush();
			fail("Should not pass to this line : TestSizeStaffPhoneError");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.2 > TestSizeStaffPhoneErrorLong :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}

	// ทดสอบ size phone น้อยกว่า 12
	@Test
	public void TestSalaryPhonePatternError() {
		Salary sa = new Salary();
		sa.setSalaryId(1L);
		sa.setSalaryIds("SA1");
		sa.setSalaryDate(sa.getSalaryDate());
		sa.setStaff(staffRepository.findByStaffId(1L));
		sa.setStaff(staffRepository.findByStaffPhone("1861419833")); // Testing
		sa.setStaff(staffRepository.findByStaffSalary(50000));
		sa.setPayer(payerRepository.findByPayerId(1L));
		salaryRepository.save(sa);
		try {
			// entityManager.persist(sa);
			entityManager.flush();
			fail("Should not pass to this line : TestSalaryPhonePatternError");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.3 > TestSalaryPhonePatternError :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}

	// ทดสอบ StaffName ไม่เหมือนกัน
	@Test
	public void TestUniqeStaffNameError() {

		Salary sa1 = new Salary();
		sa1.setSalaryId(9L);
		sa1.setSalaryIds("SA9");
		sa1.setSalaryDate(new Date());
		sa1.setStaff(staffRepository.findByStaffId(1L));
		sa1.setPayer(payerRepository.findByPayerId(1L));

		salaryRepository.save(sa1);
		entityManager.flush();

		Salary sa2 = new Salary();
		sa2.setSalaryId(9L);
		sa2.setSalaryIds("SA9");
		sa2.setSalaryDate(new Date());
		sa2.setStaff(staffRepository.findByStaffId(1L));
		sa2.setPayer(payerRepository.findByPayerId(1L));

		try {
			entityManager.persist(sa2);
			entityManager.flush();

		} catch (javax.persistence.PersistenceException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.5 > TestUniqeStaffNameError :");
			System.out.println(e);
			System.out.println();
			System.out.println();

		}
	}
}