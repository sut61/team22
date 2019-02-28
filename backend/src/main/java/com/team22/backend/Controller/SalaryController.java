package com.team22.backend.Controller;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController

class SalaryController {
    @Autowired
    private SalaryRepository salaryRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private EducationRepository educationRepository;
    @Autowired
    private ExperienceRepository experienceRepository;
    @Autowired
    private PayerRepository payerRepository;

    @Autowired
    SalaryController(SalaryRepository salaryRepository,
                    StaffRepository staffRepository,
                    PositionRepository positionRepository,
                    EducationRepository educationRepository,
                    ExperienceRepository experienceRepository,
                    PayerRepository payerRepository
    ){
        this.salaryRepository = salaryRepository;
        this.staffRepository = staffRepository;
        this.payerRepository = payerRepository;
        this.educationRepository = educationRepository;
        this.experienceRepository = experienceRepository;
    }

    @GetMapping("/salary2")
    public Collection<Salary> salary2() {
        return salaryRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/salary")
        public Collection<Salary> salary() {
            return salaryRepository.findAll().stream()
                    .filter(this::isSalary)
                    .collect(Collectors.toList());
        }
        private boolean isSalary(Salary salary){
            return salary.getStaff().getStaffStatus().equals("UnPaid");
        }
    @GetMapping("/salary1")
        public Collection<Salary> salary1() {
            return salaryRepository.findAll().stream()
                    .filter(this::isSalary1)
                    .collect(Collectors.toList());
        }
        private boolean isSalary1(Salary salary){
            return salary.getStaff().getStaffStatus().equals("Paid");
        }
    @GetMapping("/payer")
    public Collection<Payer> payer() {
        return payerRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/salaryPost/{staffName}/{payerId}/{staffStatus}/{staffSalary}/{salaryBankId}")
    public Salary Salary1(
        @PathVariable String   staffName,
        @PathVariable Long   payerId,
        @PathVariable String   staffStatus, 
        @PathVariable int   staffSalary,
        @PathVariable String salaryBankId
         ){
        Salary salary = new Salary();
        for(Long i=1L; i<9999L;i++) {
            if(salaryRepository.findBySalaryId(i) == null) {
                salary.setSalaryIds("SA"+i);
                break;
            }
        }

        salary.setSalaryDate(new Date());
        Staff staff1 = staffRepository.findByStaffName(staffName);
        Payer payer1 = payerRepository.findByPayerId(payerId);
        salary.setStaff(staff1);
        salary.getStaff().setStaffStatus(staffStatus);
        salary.getStaff().setStaffSalary(staffSalary);
        salary.setSalaryBankId(salaryBankId);
        salary.setPayer(payer1);
    return salaryRepository.save(salary);
    }

    @PutMapping("/salary/{salaryId}/{staffId}/{staffStatus}/{salaryDate}/{staffSalary}/{salaryBankId}")
    Salary Salary2(
            Salary newSalary,
            @PathVariable String staffStatus,
            @PathVariable Long staffId,
            @PathVariable Long salaryId,
            @PathVariable int staffSalary,
            @PathVariable Date salaryDate,
            @PathVariable String salaryBankId
            ) {
        Staff staff1 = staffRepository.findByStaffId(staffId);
        return salaryRepository.findById(salaryId)
                .map(salary -> {
                    salary.setSalaryDate(new Date());
                    salary.getStaff().setStaffSalary(staffSalary);
                    salary.getStaff().setStaffStatus(staffStatus);
                    salary.setSalaryBankId(salaryBankId);
                    return salaryRepository.save(salary);
                }
                ).orElseGet(() -> {
                    newSalary.setStaff(staff1);
                    return salaryRepository.save(newSalary);
                });
    }
    
}