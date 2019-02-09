package com.team22.backend.Controller;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.*;
import java.util.*;
import java.text.*;
import java.util.Collection;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class CheckController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CheckProductRepository checkProductRepository;
    @Autowired
    private CheckingRepository checkingRepository;

    public static void stringToDate (String args[]) {
        try { 
           System.out.println(new Date( ) + "\n"); 
           Thread.sleep(5*60*10); 
           System.out.println(new Date( ) + "\n"); 
        } catch (Exception e) {
           System.out.println("Got an exception!"); 
        }
     }

    @GetMapping(path = "/checkproduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CheckProduct> checkProduct() {
        return checkProductRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @GetMapping(path = "/checking", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection <Checking> checking() {
        return checkingRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/checkproduct/{prodId}/{checkLevel}/{checkComment}/{checkDate}/{checkTime}/{checkingId}")
    public CheckProduct newCheckproduct(@PathVariable Long prodId, @PathVariable Integer checkLevel,
    @PathVariable String checkComment,@PathVariable String checkDate,@PathVariable String checkTime,@PathVariable Long checkingId)
    { 
        Checking setCh = checkingRepository.findByCheckingId(checkingId);
        String chDate = checkDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate date = LocalDate.parse(chDate,formatter);

        SimpleDateFormat ft = new SimpleDateFormat ("HH:mm"); 
        Date ti = new Date();
        try {
           ti = ft.parse(checkTime); 
             System.out.println(ti); 
        } catch (ParseException e) { 
         System.out.println("Unparseable using " + ft); 
         }
        Instant instant = Instant.ofEpochMilli(ti.getTime());
        LocalTime time = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalTime();

        Product setProd = productRepository.findByProdId(prodId);
        CheckProduct newCheck = new CheckProduct();
        newCheck.setCheckLevel(checkLevel);
        newCheck.setCheckComment(checkComment);
        newCheck.setProduct(setProd);
        newCheck.setCheckDate(date);
        newCheck.setCheckTime(time);
        newCheck.setChecked(setCh);
        return checkProductRepository.save(newCheck);
    }
    @DeleteMapping("/checkproduct/{checkId}")
    public void deletecheckproduct(@PathVariable Long checkId) {
        checkProductRepository.deleteById(checkId);
    }
    @PutMapping("/checkproduct/editcheck/{prodId}/{checkId}/{checkLevel}/{checkComment}/{checkingId}")
    public CheckProduct editcheckproduct(@RequestBody CheckProduct checkp, @PathVariable Long prodId,@PathVariable Long checkId
    , @PathVariable Integer checkLevel, @PathVariable String checkComment,@PathVariable Long checkingId) {
        Checking    setCh = checkingRepository.findByCheckingId(checkingId);
        CheckProduct setchP = checkProductRepository.findByCheckId(checkId);
        return checkProductRepository.findById(prodId).map(checkEdit -> {
                    checkEdit.setCheckLevel(checkLevel);
                    checkEdit.setCheckComment(checkComment);
                    checkEdit.setChecked(setCh);
                    return checkProductRepository.save(checkEdit);
                }
        ).orElseGet(() -> {
            return checkProductRepository.save(checkp);
        });
    }
}