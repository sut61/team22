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
                           BookingRepository bookingRepository,
                           CustomerRepository customerRepository,
                           StyleRepository styleRepository
    ) {
        return args -> {

            Stream.of("Sunvo", "Ploy", "Ao" ,"Wahn","Opal","Meen").forEach(cusName -> {
                Customer customerdb = new Customer();
                customerdb.setCustomerName(cusName);
                customerRepository.save(customerdb);

                if (cusName == "Sunvo") {
                    Customer cusid = customerRepository.findByCusId(1L);
                    customerdb.setCustomerIDs("C"+cusid.getCusId());
                    customerdb.setAddressCustomer("Korat");
                    customerRepository.save(customerdb);

                }
                else if(cusName == "Ploy"){
                    Customer cusid = customerRepository.findByCusId(2L);
                    customerdb.setCustomerIDs("C"+cusid.getCusId());
                    customerdb.setAddressCustomer("Suranaree");
                    customerRepository.save(customerdb);
                }
                else if(cusName == "Ao"){
                    Customer cusid = customerRepository.findByCusId(3L);
                    customerdb.setCustomerIDs("C"+cusid.getCusId());
                    customerdb.setAddressCustomer("Sungneon");
                    customerRepository.save(customerdb);
                }
                else if(cusName == "Wahn"){
                    Customer cusid = customerRepository.findByCusId(4L);
                    customerdb.setCustomerIDs("C"+cusid.getCusId());
                    customerdb.setAddressCustomer("Krathok");
                    customerRepository.save(customerdb);
                }
                else if(cusName == "Opal"){
                    Customer cusid = customerRepository.findByCusId(5L);
                    customerdb.setCustomerIDs("C"+cusid.getCusId());
                    customerdb.setAddressCustomer("Khonkan");
                    customerRepository.save(customerdb);
                }
                else if(cusName == "Meen"){
                    Customer cusid = customerRepository.findByCusId(6L);
                    customerdb.setCustomerIDs("C"+cusid.getCusId());
                    customerdb.setAddressCustomer("Korat");
                    customerRepository.save(customerdb);
                }
            });
            Customer c1 = customerRepository.findByCusId(1L);
            Customer c2 = customerRepository.findByCusId(2L);
            Customer c3 = customerRepository.findByCusId(3L);
            Customer c4 = customerRepository.findByCusId(4L);
            Customer c5 = customerRepository.findByCusId(5L);
            Customer c6 = customerRepository.findByCusId(6L);

            Stream.of("korea style", "thai style", "laos style","wedding", "thai wedding", "chinese style").forEach(styName -> {
                Style styledb = new Style();
                styledb.setStyleName(styName);
                styleRepository.save(styledb);

                if (styName == "korea style") {
                    Style styleID = styleRepository.findBystyleID(1L);
                    styledb.setStyleIDs("Sy"+styleID.getStyleID());
                    styledb.setStylePrice(3000);
                    styleRepository.save(styledb);
                }
                else if(styName == "thai style"){
                    Style styleID = styleRepository.findBystyleID(2L);
                    styledb.setStyleIDs("Sy"+styleID.getStyleID());
                    styledb.setStylePrice(2000);
                    styleRepository.save(styledb);
                }
                else if(styName == "laos style"){
                    Style styleID = styleRepository.findBystyleID(3L);
                    styledb.setStyleIDs("Sy"+styleID.getStyleID());
                    styledb.setStylePrice(1700);
                    styleRepository.save(styledb);
                }
                else if(styName == "wedding"){
                    Style styleID = styleRepository.findBystyleID(4L);
                    styledb.setStyleIDs("Sy"+styleID.getStyleID());
                    styledb.setStylePrice(5000);
                    styleRepository.save(styledb);
                }
                else if(styName == "thai wedding"){
                    Style styleID = styleRepository.findBystyleID(5L);
                    styledb.setStyleIDs("Sy"+styleID.getStyleID());
                    styledb.setStylePrice(4500);
                    styleRepository.save(styledb);
                }
                else if(styName == "chinese style"){
                    Style styleID = styleRepository.findBystyleID(6L);
                    styledb.setStyleIDs("Sy"+styleID.getStyleID());
                    styledb.setStylePrice(2500);
                    styleRepository.save(styledb);
                }
            });
            Style sy1 = styleRepository.findBystyleID(1L);
            Style sy2 = styleRepository.findBystyleID(2L);
            Style sy3 = styleRepository.findBystyleID(3L);
            Style sy4 = styleRepository.findBystyleID(4L);
            Style sy5 = styleRepository.findBystyleID(5L);
            Style sy6 = styleRepository.findBystyleID(6L);

            Stream.of("Stylist","Service","HairStylist", "MakeupArtist","Renter","Seller","Accountant").forEach(posName -> {
                Position positiondb = new Position();
                positiondb.setPositionName(posName);
                positionRepository.save(positiondb);

                if (posName == "Stylist") {
                    positiondb.setPositionIds("P"+positiondb.getPositionId());
                    positiondb.setPositionName(posName);
                    positionRepository.save(positiondb);
                }

                else if(posName == "Service") {
                    positiondb.setPositionIds("P"+positiondb.getPositionId());
                    positiondb.setPositionName(posName);
                    positionRepository.save(positiondb);
                }

                else if (posName == "HairStylist") {
                    positiondb.setPositionIds("P"+positiondb.getPositionId());
                    positiondb.setPositionName(posName);
                    positionRepository.save(positiondb);
                }

                else if(posName == "MakeupArtist") {
                    positiondb.setPositionIds("P"+positiondb.getPositionId());
                    positiondb.setPositionName(posName);
                    positionRepository.save(positiondb);
                }

                else if(posName == "Renter") {
                    positiondb.setPositionIds("P"+positiondb.getPositionId());
                    positiondb.setPositionName(posName);
                    positionRepository.save(positiondb);
                }

                else if(posName == "Seller") {
                    positiondb.setPositionIds("P"+positiondb.getPositionId());
                    positiondb.setPositionName(posName);
                    positionRepository.save(positiondb);
                }

                else if(posName == "Accountant") {
                    positiondb.setPositionIds("P"+positiondb.getPositionId());
                    positiondb.setPositionName(posName);
                    positionRepository.save(positiondb);
                }
            });

            Position po1 = positionRepository.findByPositionId(1L);
            Position po2 = positionRepository.findByPositionId(2L);
            Position po3 = positionRepository.findByPositionId(3L);
            Position po4 = positionRepository.findByPositionId(4L);
            Position po5 = positionRepository.findByPositionId(5L);
            Position po6 = positionRepository.findByPositionId(6L);
            Position po7 = positionRepository.findByPositionId(7L);

            Stream.of("Owner", "Ploy", "Sunvo" , "Au").forEach(staffName -> {
                Staff staffdb = new Staff();
                staffdb.setStaffName(staffName);
                staffRepository.save(staffdb);

                if (staffName == "Owner") {
                    staffdb.setStaffIds("St"+staffdb.getStaffId());
                    staffdb.setStaffName(staffName);
                    staffdb.setPosition(po6);
                    staffRepository.save(staffdb);
                }
                else    if (staffName == "Ploy") {
                    staffdb.setStaffIds("St"+staffdb.getStaffId());
                    staffdb.setStaffName(staffName);
                    staffdb.setPosition(po5);
                    staffRepository.save(staffdb);
                }
                else    if (staffName == "Sunvo") {
                    staffdb.setStaffIds("St"+staffdb.getStaffId());
                    staffdb.setStaffName(staffName);
                    staffdb.setPosition(po4);
                    staffRepository.save(staffdb);
                }
                else    if (staffName == "Au") {
                    staffdb.setStaffIds("St"+staffdb.getStaffId());
                    staffdb.setStaffName(staffName);
                    staffdb.setPosition(po3);
                    staffRepository.save(staffdb);
                }
            });
            Staff st1 = staffRepository.findByStaffId(1L);
            Staff st2 = staffRepository.findByStaffId(2L);

            Booking bookingdb = new Booking();
            String bDate1 = "20:04:1998";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate bdate = LocalDate.parse(bDate1, formatter);
            bookingRepository.save(bookingdb);

            bookingdb.setBookingDate(bdate);
            bookingdb.setCustomer(c3);
            bookingdb.setStaff(st1);
            bookingdb.setStyle(sy1);
            bookingRepository.save(bookingdb);


		Selling sellingdb = new Selling();
            sellingRepository.save(sellingdb);
            String sDate2 = "20:04:1998";
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate sdate = LocalDate.parse(sDate2, formatter2);

            sellingdb.setSellingDate(sdate);
            sellingdb.setCustomer(c2);
            sellingdb.setStaff(st1);
            sellingdb.setStatus("not paid");
            sellingdb.setProduct(pt1);
            sellingRepository.save(sellingdb);
        
        System.out.println("\n Spring-Boot Complete");
        };
    }
}