package com.team22.backend.Controller;
import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BookingCancleController {
    @Autowired
    private final BookingCancleRepository bookingCancleRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private TypeReasonRepository typeReasonRepository;

    public BookingCancleController(BookingCancleRepository bookingCancleRepository, 
                                   BookingRepository bookingRepository,
                                   TypeReasonRepository typeReasonRepository) {
                                    this.bookingRepository = bookingRepository;
                                    this.bookingCancleRepository = bookingCancleRepository;
                                    this.typeReasonRepository = typeReasonRepository;
                                }
    
    @GetMapping("/cbooking")
        public Collection<Booking> booking() {
            return bookingRepository.findAll().stream()
            .filter(this::isBooking)
            .collect(Collectors.toList());
        }
        private boolean isBooking(Booking booking){
            return booking.getStatusBooking().equals("Booking");
        }


    @GetMapping("/typeReason")
        public Collection<TypeReason> typeReasons() {
            return typeReasonRepository.findAll().stream()
            .collect(Collectors.toList());
    }

    @GetMapping("/bookingCancle")
        public Collection<BookingCancle> bookingCancles() {
            return bookingCancleRepository.findAll().stream()
            .collect(Collectors.toList());
    }
    @PostMapping("checkbookingCancleReason/{cancleReason}") 
        public BookingCancle checkbookingCancleReason(@PathVariable String cancleReason) {
            return this.bookingCancleRepository.findByBookingCancleReason(cancleReason);
        }
          
        
    @PostMapping("/bookingCanclecon/{bookingId}/{bookingCancleReason}/{typeReasonName}")
    public BookingCancle newBookingCancles(@PathVariable Long bookingId,
                                        @PathVariable String bookingCancleReason,
                                        @PathVariable Long typeReasonName)
                                         {

        BookingCancle newBookingCancle = new BookingCancle();
        Long i;
        for( i=1L; i<9999L;i++) {
            if(bookingCancleRepository.findByBookingCancleID(i) == null) {
                newBookingCancle.setBookingCancleIDs("Bc"+i);
                break;
            }
        }
        newBookingCancle.setBookingCancleReason(bookingCancleReason);
        newBookingCancle.setBookingCancleStatus("Cancled");

        Date bookingCancleDate = new Date();
        newBookingCancle.setBookingCancleDate(bookingCancleDate);

        Booking booking = bookingRepository.findByBookingId(bookingId);
        newBookingCancle.setBooking(booking);

        TypeReason setTR = typeReasonRepository.findByTypeReasonID(typeReasonName);
        newBookingCancle.setTypeReason(setTR);
      
        return bookingCancleRepository.save(newBookingCancle);
    }

    @PutMapping("/cancleStatus/{bookingCancleID}/{bookingId}/{statusBooking}")
     BookingCancle replaceBookingCancle( BookingCancle newBookingCancle,
                                         @PathVariable Long bookingId,
                                         @PathVariable Long bookingCancleID,
                                         @PathVariable String statusBooking ){

     
    return bookingCancleRepository.findById(bookingCancleID)
                .map(booking ->{
                booking.getBooking().setStatusBooking(statusBooking);
                booking.setBookingCancleDate(new Date());
                return bookingCancleRepository.save(booking);
            }
            ).orElseGet(() ->{
                Booking booking1 = bookingRepository.findByBookingId(bookingId); 
                newBookingCancle.setBooking(booking1);
                newBookingCancle.setBookingCancleID(bookingCancleID);
                return bookingCancleRepository.save(newBookingCancle);
    
            });
        }
}