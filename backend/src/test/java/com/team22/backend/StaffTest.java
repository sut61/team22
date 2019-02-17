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
public class StaffTest {
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
	public void TestStaffTrueData() {

		Staff s = new Staff();
		s.setStaffId(1L);
		s.setStaffIds("St1");
		s.setStaffName("Owner");
		s.setStaffPassword("Chayanun123456789");
		s.setStaffGender("Man");
		s.setStaffJobtype("Full Time");
		s.setStaffPhone("086-141-9833");
		s.setStaffSalary(50000);
		s.setStaffStatus("UnPaid");
		s.setPosition(positionRepository.findByPositionId(6L));
		s.setExperience(experienceRepository.findByExperienceId(1L));
		s.setEducation(educationRepository.findByEducationId(1L));
		staffRepository.save(s);
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
			fail("Should not pass to this line : TestStaffTrueData");

		}
	}

	// ทดสอบข้อมูล Staff ไม่ null
	@Test
	public void TestStaffCannotBeNull() {

		Staff s = new Staff();
		s.setStaffId(null);
		s.setStaffIds(null);
		s.setStaffName(null);
		s.setStaffGender(null);
		s.setStaffJobtype(null);
		s.setStaffPassword(null);
		s.setStaffPhone(null);
		s.setStaffSalary(null);
		s.setStaffStatus(null);
		s.setPosition(positionRepository.findByPositionId(null));
		s.setExperience(experienceRepository.findByExperienceId(null));
		s.setEducation(educationRepository.findByEducationId(null));

		try {
			entityManager.persist(s);
			entityManager.flush();
			fail("Should not pass to this line : TestStaffCannotBeNull");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.1 > TestStaffNameCannotBeNull :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 8);
		}
	}

	@Test
	public void TestPositionCannotBeNull() {

		Position po = new Position();
		po.setPositionId(null);
		po.setPositionIds(null);
		po.setPositionName(null);

		try {
			entityManager.persist(po);
			entityManager.flush();
			fail("Should not pass to this line : TestPositionCannotBeNull");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.1 > TestPositionCannotBeNull :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	@Test
	public void TestEducationCannotBeNull() {

		Education ed = new Education();
		ed.setEducationId(null);
		ed.setEducationIds(null);
		ed.setEducationName(null);

		try {
			entityManager.persist(ed);
			entityManager.flush();
			fail("Should not pass to this line : TestEducationCannotBeNull");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.1 > TestEducationCannotBeNull :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	@Test
	public void TestExperenceCannotBeNull() {

		Experience ex = new Experience();
		ex.setExperienceId(null);
		ex.setExperienceIds(null);
		ex.setExperienceName(null);

		try {
			entityManager.persist(ex);
			entityManager.flush();
			fail("Should not pass to this line : TestExperenceCannotBeNull");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.1 > TestExperenceCannotBeNull :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	// ทดสอบ PatternStaffPhone ผิด
	@Test
	public void TestFalsePatternStaffPhone() {
		Staff s = new Staff();
		s.setStaffId(9L);
		s.setStaffIds("St9");
		s.setStaffName("Owner");
		s.setStaffGender("Man");
		s.setStaffJobtype("Full Time");
		s.setStaffPhone("186-141-9833");				//Test
		s.setStaffPassword("Chayanun123456789");
		s.setStaffSalary(50000);
		s.setStaffStatus("UnPaid");
		s.setPosition(positionRepository.findByPositionId(1L));
		s.setExperience(experienceRepository.findByExperienceId(1L));
		s.setEducation(educationRepository.findByEducationId(1L));
		staffRepository.save(s);
		try {
			entityManager.flush();
			fail("Should not pass to this line : TestFalsePatternStaffPhone");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.2 > TestFalsePatternStaffPhone :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 1);

		}
	}

	// ทดสอบ StaffPhone มี size เกิน 12
	@Test
	public void TestSizeStaffPhoneToMuch() {
		Staff s = new Staff();
		s.setStaffId(9L);
		s.setStaffIds("St9");
		s.setStaffName("Owner");
		s.setStaffGender("Man");
		s.setStaffJobtype("Full Time");
		s.setStaffPhone("086-141-98333");		//Test
		s.setStaffPassword("Chayanun123456789");
		s.setStaffSalary(50000);
		s.setStaffStatus("UnPaid");
		s.setPosition(positionRepository.findByPositionId(1L));
		s.setExperience(experienceRepository.findByExperienceId(1L));
		s.setEducation(educationRepository.findByEducationId(1L));

		staffRepository.save(s);

		try {
			entityManager.flush();
			fail("Should not pass to this line : TestSizeStaffPhoneToMuch");
		} catch (javax.validation.ConstraintViolationException e) {
			Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.4 > TestSizeStaffPhoneToMuch :");
			System.out.println(e);
			System.out.println();
			System.out.println();
			assertEquals(violations.isEmpty(), false);
			assertEquals(violations.size(), 2);
		}
	}

	// ทดสอบ Staff ไม่เหมือนกัน
	@Test
	public void TestUniqe() {

		Staff s1 = new Staff();
		s1.setStaffName("Owner1");
		s1.setStaffIds("St9");
		s1.setStaffGender("Man");
		s1.setStaffJobtype("Full Time");
		s1.setStaffPhone("086-141-9833");
		s1.setStaffPassword("Chayanun0861419833");
		s1.setStaffSalary(50000);
		s1.setStaffStatus("UnPaid");
		s1.setPosition(positionRepository.findByPositionId(1L));
		s1.setExperience(experienceRepository.findByExperienceId(1L));
		s1.setEducation(educationRepository.findByEducationId(1L));

		entityManager.persist(s1);
		entityManager.flush();

		Staff s2 = new Staff();
		s2.setStaffName("Owner1");
		s2.setStaffIds("St9");
		s2.setStaffGender("Man");
		s2.setStaffJobtype("Full Time");
		s2.setStaffPhone("086-141-9833");
		s2.setStaffPassword("Chayanun0861419833");
		s2.setStaffSalary(50000);
		s2.setStaffStatus("UnPaid");
		s2.setPosition(positionRepository.findByPositionId(1L));
		s2.setExperience(experienceRepository.findByExperienceId(1L));
		s2.setEducation(educationRepository.findByEducationId(1L));
		try {
			entityManager.persist(s2);
			entityManager.flush();

		} catch (javax.persistence.PersistenceException e) {
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println("2.5 > TestUniqe :");
			System.out.println(e);
			System.out.println();
			System.out.println();

		}
	}

}
