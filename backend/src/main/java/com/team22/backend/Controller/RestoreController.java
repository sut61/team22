package com.team22.backend.Controller;

import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;

import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class RestoreController {
    @Autowired
    private final RestoreRepository restoreRepository;
    @Autowired
    private LeaseRepository leaseRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private RestoreTypeRepository restoreTypeRepository;

    public RestoreController (RestoreRepository restoreRepository, 
                                    LeaseRepository leaseRepository, 
                                   CustomerRepository customerRepository,
                                   ProductRepository productRepository,
                                   RestoreTypeRepository restoreTypeRepository) {
        this.restoreRepository = restoreRepository;
        this.leaseRepository = leaseRepository;
        this.customerRepository = customerRepository;
        this.productRepository =  productRepository;
    }

    @GetMapping("/rlease")
        public Collection<Lease>Rlease() {
            return leaseRepository.findAll().stream()
            .filter(this::isRlease)
            .collect(Collectors.toList());
        }
        private boolean isRlease(Lease lease){
            return lease.getStatus().equals("paid");
        }
    
    
    @GetMapping("/restore")
        public Collection<Restore> restore() {
            return restoreRepository.findAll().stream()
                    .filter(this::isRestore)
                    .collect(Collectors.toList());
        }
        private boolean isRestore(Restore restore){
            return restore.getLease().getLeaseStatus().equals("Rent");
   }

   @GetMapping(path = "/restoretype", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<RestoreType> restoreType() {
        return restoreTypeRepository.findAll().stream().collect(Collectors.toList());
    }

    @PostMapping("/CheckCommentRestore/{comment}")
    public Restore CheckCommentRestore(@PathVariable String comment) {
        return this.restoreRepository.findByCommentRestore(comment);
    }
  
    @PostMapping("/restore1/{customerIDs}/{customerName}/{leaseId}/{productName}/{commentRestore}/{typeRestoreName}")
     public Restore newRestore(@PathVariable Long leaseId,@PathVariable String commentRestore,
                                @PathVariable String customerName, @PathVariable String customerIDs,
                                  @PathVariable String productName,@PathVariable Long typeRestoreName ){
          Restore newRestore = new Restore();
          Lease leaseid = leaseRepository.findByLeaseId(leaseId);
          newRestore.setCommentRestore(commentRestore);
          Date dateRestore= new Date();
          newRestore.setDateRestore(dateRestore);
          newRestore.setLease(leaseid);
          newRestore.setStatusRestore("rent");
          RestoreType setRt = restoreTypeRepository.findByRestoreTypeId(typeRestoreName);
          newRestore.setRestoreType(setRt);
        
          return restoreRepository.save(newRestore); 

     }
     @PutMapping("/restorestatus/{id}")
     Restore replaceRestore(Restore newRestore, @PathVariable Long id){

    return restoreRepository.findById(id)
                .map(restore ->{
                restore.setStatusRestore("restore");
                return restoreRepository.save(restore);
            }
            ).orElseGet(() ->{
                newRestore.setRestoreId(id);
                return restoreRepository.save(newRestore);
    });
}
}
