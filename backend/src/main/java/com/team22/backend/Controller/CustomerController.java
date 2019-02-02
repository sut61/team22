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
        private StaffRepository staffRepository;
        public CustomerController(CustomerRepository customerRepository,
                                 StaffRepository staffRepository) {
            this.customerRepository = customerRepository; 
            this.staffRepository = staffRepository;
        } 
    
        @PostMapping("/customerLogin/{customerIDs}/password/{customerPassword}")
        public Customer customerLogin(@PathVariable String customerIDs , @PathVariable String customerPassword){
            return this.customerRepository.findByCustomerIDsAndCustomerPassword(customerIDs,customerPassword);
        }
        @PostMapping("/adminLogin/{admin}/password/{Password}")
        public Staff adminLogin(@PathVariable String admin , @PathVariable String Password){
            return this.staffRepository.findByStaffNameAndStaffPassword(admin,Password);
        }
  
}

    


