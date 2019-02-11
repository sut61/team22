package com.team22.backend.Controller;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.http.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {
    String message;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CareerRepository careerRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private StaffRepository staffRepository;

    public CustomerController(CustomerRepository customerRepository, CareerRepository careerRepository,
            ProvinceRepository provinceRepository, StaffRepository staffRepository) {
        this.customerRepository = customerRepository;
        this.careerRepository = careerRepository;
        this.provinceRepository = provinceRepository;
        this.staffRepository = staffRepository;
    }

    @PostMapping("/customerSignin/{customerIDs}/{customerPassword}")
    public Customer customerLogin(@PathVariable String customerIDs, @PathVariable String customerPassword) {
        return this.customerRepository.findByCustomerIDsAndCustomerPassword(customerIDs, customerPassword);
    }

    @PostMapping("/adminLogin/{admin}/password/{Password}")
    public Staff adminLogin(@PathVariable String admin, @PathVariable String Password) {
        return this.staffRepository.findByStaffNameAndStaffPassword(admin, Password);
    }

    @PostMapping("/customerCheck/{customerIDs}")
    public Customer customerCheck(@PathVariable String customerIDs) {
        return this.customerRepository.findByCustomerIDs(customerIDs);
    }

    @GetMapping("/career")
    public Collection<Career> career() {
        return careerRepository.findAll().stream().collect(Collectors.toList());
    }

    @GetMapping("/province")
    public Collection<Province> province() {
        return provinceRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/customerSignup/{customerIDs}/{customerPassword}/{customerName}/{customerPhone}/{customerGender}/{customerBirthday}/{customerAddress}/{careerId}/{provinceId}")
    public Customer newCustomer(@PathVariable String customerIDs, @PathVariable String customerPassword,
            @PathVariable String customerName, @PathVariable String customerPhone, @PathVariable String customerGender,
            @PathVariable String customerBirthday, @PathVariable String customerAddress, @PathVariable Long careerId,
            @PathVariable Long provinceId) {

        Customer newCustomer = new Customer();

        newCustomer.setCustomerIDs(customerIDs);
        newCustomer.setCustomerPassword(customerPassword);
        newCustomer.setCustomerName(customerName);
        newCustomer.setCustomerPhone(customerPhone);
        newCustomer.setCustomerGender(customerGender);

        String sDate1 = customerBirthday;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate date = LocalDate.parse(sDate1, formatter);
        newCustomer.setCustomerBirthday(date);
        newCustomer.setCustomerAddress(customerAddress);

        Career career = careerRepository.findByCareerId(careerId);
        newCustomer.setCareer(career);

        Province province = provinceRepository.findByProvinceId(provinceId);
        newCustomer.setProvince(province);

        return customerRepository.save(newCustomer);
    }

}
