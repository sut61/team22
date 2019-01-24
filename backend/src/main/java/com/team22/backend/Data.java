package com.team22.backend;
import com.team22.backend.Repository.*;
import com.team22.backend.Entity.*;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:4200")
@SpringBootApplication
public class Data {

//    Date date = new Date();
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Data.class, args);
    }


    @Bean
    CharacterEncodingFilter characterEncodingFilter() {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        return filter;
    }

    @Bean
    ApplicationRunner init(PositionRepository positionRepository,
                           StaffRepository staffRepository,
                           SellingRepository sellingRepository,
                           EducationRepository educationRepository,
                           DetailRepository detailRepository,
                           ProductRepository productRepository,
                           StatusRepository statusRepository,
                           BookingRepository bookingRepository,
                           CustomerRepository customerRepository,
                           TypeRepository typeRepository,
                           StyleRepository styleRepository,
                           LeaseRepository leaseRepository,
                           PayMentRepository  payMentRepository,
                           ExperienceRepository experienceRepository
                           ) {
        return args -> {

            Stream.of("Renting", "Selling", "Stocking").forEach(status -> {
                Status state = new Status(status);
                statusRepository.save(state);
            });
            Status sta1 = statusRepository.findByStateId(1L);
            Status sta2 = statusRepository.findByStateId(2L);
            Status sta3 = statusRepository.findByStateId(3L);
            Stream.of("Cloth", "Shoes", "Cosmetic", "Hair", "Accessories", "OtherType").forEach(tName -> {
                Type typeprodName = new Type(tName);
                typeRepository.save(typeprodName);
            });
            Stream.of("Bust", "Waist", "Hip", "Length", "Size", "OtherDetail").forEach(dName -> {
                Detail dprodName = new Detail(dName);
                detailRepository.save(dprodName);
            });
            System.out.println("\n Spring-Boot Complete");
        };
    }
}