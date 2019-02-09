package com.team22.backend.Repository;

import com.team22.backend.Entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.webmvc.RepositoryRestController;

@RepositoryRestController
@EnableJpaRepositories
@CrossOrigin(origins = "http://localhost:4200")
@RepositoryRestResource
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findByCusId(Long id);

    Customer findByCustomerIDs(String customerIDs);

    Customer findByCustomerName(String customerName);

    Customer findByCustomerPassword(String customerPassword);

    Customer findByCustomerPhone(String customerPhone);

    Customer findByCustomerGender(String customerGender);

    Customer findByCustomerIDsAndCustomerPassword(String customerIDs, String customerPassword);

}