package com.team22.backend.Controller;
import com.team22.backend.Entity.*;
import com.team22.backend.Repository.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TypeReasonController {
    @Autowired
    private final TypeReasonRepository typeReasonRepository;
    
public TypeReasonController(TypeReasonRepository typeReasonRepository) {
        this.typeReasonRepository = typeReasonRepository;
}
}
