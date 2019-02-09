package com.team22.backend.Controller;
import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import java.util.*;
@RestController
@CrossOrigin(origins = "http://localhost:4200")

public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private SellingRepository sellingRepository;
    @Autowired
    private LevelReviewRepository levelReviewRepository;

    public ReviewController(ReviewRepository reviewRepository,
                            CustomerRepository customerRepository,
                            ProductRepository productRepository,
                            LevelReviewRepository levelReviewRepository,
                            SellingRepository sellingRepository) {
        this.reviewRepository = reviewRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.levelReviewRepository = levelReviewRepository;
        this.sellingRepository = sellingRepository;
    }
    @GetMapping(path = "/levelReview", produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<LevelReview> levelReview() {
        return levelReviewRepository.findAll().stream().collect(Collectors.toList());
    }
    @GetMapping("/review")
    public Collection<Review> review() {
        return reviewRepository.findAll().stream()
                .collect(Collectors.toList());
    }
    
    @GetMapping("/SellingReview")
    public Collection<Selling> selling() {
       return sellingRepository.findAll().stream()
               .filter(this::isSelling)
               .collect(Collectors.toList());
    }
    private boolean isSelling(Selling Selling){
       return Selling.getStatus().equals("paid");
    }

    @PostMapping("/Rev/{productID}/{productName}/{status}/{customerName}/{levelName}/{reviewDate}/{reviewComment}/{ment2}")
        public Review newRev(@PathVariable String productID,
                            @PathVariable String productName,
                            @PathVariable Long status,
                            @PathVariable String customerName,
                            @PathVariable Long levelName,
                            @PathVariable String reviewDate,
                            @PathVariable String reviewComment,
                             @PathVariable String ment2){
            Review newReview = new Review();
            Product product = productRepository.findByProductIds(productID);
            Customer customer = customerRepository.findByCustomerIDs(customerName);
            LevelReview setlevelReview = levelReviewRepository.findByLevelReviewId(levelName);
            String RDate1 = reviewDate;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate date = LocalDate.parse(RDate1,formatter);
            newReview.setLevelReview(setlevelReview);
            newReview.setReviewDate(date);
            newReview.setReviewComment(reviewComment);
            newReview.setMent2(ment2);

             Selling sellingid = sellingRepository.findBySellingId(status);
             newReview.setSelling(sellingid);

            System.out.println(date);
    
            return reviewRepository.save(newReview); 
        }



        @PutMapping("/sellingRe/{id}/{status}")
        Selling replaceSelling(Selling newSelling, @PathVariable String status, @PathVariable Long id){
    
        return sellingRepository.findById(id)
                    .map(selling ->{
                    selling.setStatus(status);
                    return sellingRepository.save(selling);
                }
                ).orElseGet(() ->{
                    newSelling.setSellingId(id);
                    return sellingRepository.save(newSelling);
        });
    }

    @PostMapping("/CheckReviewComment/{commentRe}")
    public Review CheckReviewComment(@PathVariable String commentRe) {
        return this.reviewRepository.findByReviewComment(commentRe);
    }
}