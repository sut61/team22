package com.team22.backend.Controller;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
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

    @GetMapping(path = "/checkproduct", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<CheckProduct> checkProduct() {
        return checkProductRepository.findAll().stream().collect(Collectors.toList());
    }
    
    @GetMapping(path = "/checking", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection <Checking> checking() {
        return checkingRepository.findAll().stream().collect(Collectors.toList());
    }
    @PostMapping("/checkproduct/{prodId}/{checkLevel}/{checkComment}/{checkDate}/{checkingId}")
    public CheckProduct newCheckproduct(@PathVariable Long prodId, @PathVariable Integer checkLevel,@PathVariable String checkComment,@PathVariable String checkDate,@PathVariable Long checkingId)
    {   Checking setCh = checkingRepository.findByCheckingId(checkingId);
        String chDate = checkDate;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate date = LocalDate.parse(chDate,formatter);
        Product setProd = productRepository.findByProdId(prodId);
        CheckProduct newCheck = new CheckProduct();
        newCheck.setCheckLevel(checkLevel);
        newCheck.setCheckComment(checkComment);
        newCheck.setProduct(setProd);
        newCheck.setCheckDate(date);
        newCheck.setChecking(setCh);
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
                    checkEdit.setChecking(setCh);
                    return checkProductRepository.save(checkEdit);
                }
        ).orElseGet(() -> {
            return checkProductRepository.save(checkp);
        });
    }
}