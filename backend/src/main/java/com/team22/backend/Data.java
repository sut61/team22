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
                               ExperienceRepository experienceRepository,
                               ProvinceRepository provinceRepository,
                               CareerRepository careerRepository,
                               CheckProductRepository checkProductRepository,
                               CheckingRepository checkingRepository,
                               SalaryRepository salaryRepository,
                               PayerRepository payerRepository,
                               ReviewRepository reviewRepository,
                               LevelReviewRepository levelReviewRepository,
                               TypeReasonRepository typeReasonRepository,
                               BookingCancleRepository bookingCancleRepository,
                               RestoreRepository restoreRepository,
                               RestoreTypeRepository restoreTypeRepository


                               ) {
        return args -> {

            Stream.of("Renting", "Selling", "Stocking").forEach(status -> {
                Status state = new Status(status);
                statusRepository.save(state);
            });
            Stream.of("Cloth", "Shoes", "Cosmetic", "Hair", "Accessories", "OtherType").forEach(tName -> {
                Type typeprodName = new Type(tName);
                typeRepository.save(typeprodName);
            });
            Stream.of("Bust", "Waist", "Hip", "Length", "Size", "OtherDetail").forEach(dName -> {
                Detail dprodName = new Detail(dName);
                detailRepository.save(dprodName);
            });
            Stream.of("Use","Repair","Donate","Reject","Recycle").forEach(checking -> {
                Checking checkingprod = new Checking (checking);
                checkingRepository.save(checkingprod);
            });
        Status sta1 = statusRepository.findByStateId(1L);
        Status sta2 = statusRepository.findByStateId(2L);
        Status sta3 = statusRepository.findByStateId(3L);
        Type type1 = typeRepository.findByTypeIds(1L);
       
        String pDate = ("01:02:2019");
        DateTimeFormatter formatt = DateTimeFormatter.ofPattern("dd:MM:yyyy");
        LocalDate productDate = LocalDate.parse(pDate,formatt);

            Product product = new Product();
            product.setProductName("dress");
            product.setProductIds("P45");
            product.setProductPrice(1000);
            product.setProductQuantity(18);
            product.setProductDate(productDate);
            product.setStatus(sta1);
            product.setType(type1);
            productRepository.save(product);

            Product product1 = new Product();
            product1.setProductName("Earing");
            product1.setProductIds("P49");
            product1.setProductPrice(1000);
            product1.setProductQuantity(18);
            product1.setProductDate(productDate);
            product1.setStatus(sta2);
            product1.setType(type1);
            productRepository.save(product1);
            Product pt1 = productRepository.findByProdId(1L);
            Product pt2 = productRepository.findByProdId(2L);

            Education educationdb = new Education();
            educationdb.setEducationIds("E1");
            educationdb.setEducationName("M.3");
            educationRepository.save(educationdb);

            Education educationdb1 = new Education();
            educationdb1.setEducationIds("E2");
            educationdb1.setEducationName("M.6");
            educationRepository.save(educationdb1);

            Education educationdb2 = new Education();
            educationdb2.setEducationIds("E3");
            educationdb2.setEducationName("Polytechnical College");
            educationRepository.save(educationdb2);

            Education educationdb3 = new Education();
            educationdb3.setEducationIds("E4");
            educationdb3.setEducationName("Technical College");
            educationRepository.save(educationdb3);

            Education educationdb4 = new Education();
            educationdb4.setEducationIds("E5");
            educationdb4.setEducationName("Bachelor Degrees");
            educationRepository.save(educationdb4);

            Education educationdb5 = new Education();
            educationdb5.setEducationIds("E6");
            educationdb5.setEducationName("Master Degrees");
            educationRepository.save(educationdb5);

            Education ed1 = educationRepository.findByEducationId(1L);
            Education ed2 = educationRepository.findByEducationId(2L);
            Education ed3 = educationRepository.findByEducationId(3L);
            Education ed4 = educationRepository.findByEducationId(4L);
            Education ed5 = educationRepository.findByEducationId(5L);
            Education ed6 = educationRepository.findByEducationId(6L);

            Experience experiencedb = new Experience();
            experiencedb.setExperienceIds("Ex1");
            experiencedb.setExperienceName(" 0 Year");
            experienceRepository.save(experiencedb);

            Experience experiencedb2 = new Experience();
            experiencedb2.setExperienceIds("Ex2");
            experiencedb2.setExperienceName("1-3 Year");
            experienceRepository.save(experiencedb2);

            Experience experiencedb3 = new Experience();
            experiencedb3.setExperienceIds("Ex3");
            experiencedb3.setExperienceName("4-6 Year");
            experienceRepository.save(experiencedb3);

            Experience experiencedb4 = new Experience();
            experiencedb4.setExperienceIds("Ex4");
            experiencedb4.setExperienceName("7-9 Year");
            experienceRepository.save(experiencedb4);

            Experience experiencedb5 = new Experience();
            experiencedb5.setExperienceIds("Ex5");
            experiencedb5.setExperienceName("10 to Up");
            experienceRepository.save(experiencedb5);

            Experience ex1 = experienceRepository.findByExperienceId(1L);
            Experience ex2 = experienceRepository.findByExperienceId(2L);
            Experience ex3 = experienceRepository.findByExperienceId(3L);
            Experience ex4 = experienceRepository.findByExperienceId(4L);
            Experience ex5 = experienceRepository.findByExperienceId(5L);

            Position positiondb = new Position();
            positiondb.setPositionIds("P1");
            positiondb.setPositionName("Stylist");
            positionRepository.save(positiondb);

            Position positiondb2 = new Position();
            positiondb2.setPositionIds("P2");
            positiondb2.setPositionName("Service");
            positionRepository.save(positiondb2);

            Position positiondb3 = new Position();
            positiondb3.setPositionIds("P3");
            positiondb3.setPositionName("HairStylist");
            positionRepository.save(positiondb3);

            Position positiondb4 = new Position();
            positiondb4.setPositionIds("P4");
            positiondb4.setPositionName("MakeupArtist");
            positionRepository.save(positiondb4);

            Position positiondb5 = new Position();
            positiondb5.setPositionIds("P5");
            positiondb5.setPositionName("Renter");
            positionRepository.save(positiondb5);

            Position positiondb6 = new Position();
            positiondb6.setPositionIds("P6");
            positiondb6.setPositionName("Seller");
            positionRepository.save(positiondb6);

            Position positiondb7 = new Position();
            positiondb7.setPositionIds("P7");
            positiondb7.setPositionName("Accountant");
            positionRepository.save(positiondb7);

            Position po1 = positionRepository.findByPositionId(1L);
            Position po2 = positionRepository.findByPositionId(2L);
            Position po3 = positionRepository.findByPositionId(3L);
            Position po4 = positionRepository.findByPositionId(4L);
            Position po5 = positionRepository.findByPositionId(5L);
            Position po6 = positionRepository.findByPositionId(6L);
            Position po7 = positionRepository.findByPositionId(7L);

            Staff staffdb = new Staff();
            staffdb.setStaffIds("St1");
            staffdb.setStaffName("Owner");
            staffdb.setStaffPassword("123456");
            staffdb.setEducation(ed1);
            staffdb.setExperience(ex1);
            staffdb.setStaffGender("Man");
            staffdb.setStaffJobtype("Full Time");
            staffdb.setStaffPhone("086-141-9833");
            staffdb.setStaffSalary(50000);
            staffdb.setPosition(po6);
            staffdb.setStaffStatus("UnPaid");
            staffRepository.save(staffdb);

            Staff staffdb1 = new Staff();
            staffdb1.setStaffIds("St2");
            staffdb1.setStaffName("Ploy");
            staffdb1.setStaffPassword("123456");
            staffdb1.setStaffGender("Woman");
            staffdb1.setStaffJobtype("Part Time");
            staffdb1.setEducation(ed2);
            staffdb1.setExperience(ex2);
            staffdb1.setStaffPhone("081-108-6599");
            staffdb1.setStaffSalary(29000);
            staffdb1.setPosition(po5);
            staffdb1.setStaffStatus("UnPaid");
            staffRepository.save(staffdb1);

            Staff staffdb2 = new Staff();

            staffdb2.setStaffIds("St3");
            staffdb2.setStaffName("Sunvo");
            staffdb2.setStaffPassword("123456");
            staffdb2.setStaffGender("Man");
            staffdb2.setStaffJobtype("Part Time");
            staffdb2.setEducation(ed3);
            staffdb2.setExperience(ex3);
            staffdb2.setStaffPhone("081-105-6559");
            staffdb2.setStaffSalary(20000);
            staffdb2.setPosition(po4);
            staffdb2.setStaffStatus("UnPaid");
            staffRepository.save(staffdb2);

            Staff staffdb3 = new Staff();
            staffdb3.setStaffIds("St4");
            staffdb3.setStaffName("Ao");
            staffdb3.setStaffPassword("123456");
            staffdb3.setStaffGender("Man");
            staffdb3.setStaffJobtype("Part Time");
            staffdb3.setEducation(ed4);
            staffdb3.setExperience(ex4);
            staffdb3.setStaffPhone("081-103-6559");
            staffdb3.setStaffSalary(2500);
            staffdb3.setPosition(po3);
            staffdb3.setStaffStatus("UnPaid");
            staffRepository.save(staffdb3);

            Staff st1 = staffRepository.findByStaffId(1L);
            Staff st2 = staffRepository.findByStaffId(2L);
            Staff st3 = staffRepository.findByStaffId(3L);
            Staff st4 = staffRepository.findByStaffId(4L);

            Payer payerdb = new Payer();
            payerdb.setPayerIds("PA1");
            payerdb.setPayerName("Admin1");
            payerRepository.save(payerdb);

            Payer payerdb1 = new Payer();
            payerdb1.setPayerIds("PA2");
            payerdb1.setPayerName("Admin2");
            payerRepository.save(payerdb1);

            Payer pa1 = payerRepository.findByPayerId(1L);
            Payer pa2 = payerRepository.findByPayerId(2L);

            Date sadate = new Date();

            Salary salarydb = new Salary();
            salarydb.setSalaryIds("SA1");
            salarydb.setStaff(st1);
            salarydb.setPayer(pa1);
            salarydb.setSalaryDate(sadate);
            salarydb.getStaff().getStaffStatus();
            salarydb.getStaff().getStaffSalary();
            salaryRepository.save(salarydb);

            Salary salarydb2 = new Salary();
            salarydb2.setSalaryIds("SA2");
            salarydb2.setStaff(st2);
            salarydb2.setPayer(pa1);
            salarydb2.setSalaryDate(sadate);
            salarydb2.getStaff().getStaffStatus();
            salarydb2.getStaff().getStaffSalary();
            salaryRepository.save(salarydb2);

            Salary salarydb3 = new Salary();
            salarydb3.setSalaryIds("SA3");
            salarydb3.setStaff(st3);
            salarydb3.setPayer(pa2);
            salarydb3.setSalaryDate(sadate);
            salarydb3.getStaff().getStaffStatus();
            salarydb3.getStaff().getStaffSalary();
            salaryRepository.save(salarydb3);

            Salary salarydb4 = new Salary();
            salarydb4.setSalaryIds("SA4");
            salarydb4.setStaff(st4);
            salarydb4.setPayer(pa2);
            salarydb4.setSalaryDate(sadate);
            salarydb4.getStaff().getStaffStatus();
            salarydb4.getStaff().getStaffSalary();
            salaryRepository.save(salarydb4);


            String dateB1 = "20:04:1997";
            String dateB2 = "21:05:1996";
            DateTimeFormatter lformatterb = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate dateB1c = LocalDate.parse(dateB1, lformatterb);
            LocalDate dateB2c = LocalDate.parse(dateB2, lformatterb);

            Stream.of("กระบี่", "กรุงเทพมหานคร", "กาญจนบุรี", "กาฬสินธุ์", "กำแพงเพชร", "ขอนแก่น", "จันทบุรี",
                    "ฉะเชิงเทรา", "ชลบุรี", "ชัยนาท", "ชัยภูมิ", "ชุมพร", "เชียงราย", "เชียงใหม่", "ตรัง", "ตราด",
                    "ตาก", "นครนายก", "นครปฐม", "นครพนม", "นครราชสีมา", "นครศรีธรรมราช", "นครสวรรค์", "นนทบุรี",
                    "นราธิวาส", "น่าน", "บุรีรัมย์", "บึงกาฬ", "ปทุมธานี", "ประจวบคีรีขันธ์", "ปราจีนบุรี", "ปัตตานี",
                    "พะเยา", "พังงา", "พัทลุง", "พิจิตร", "พิษณุโลก", "เพชรบุรี", "เพชรบูรณ์", "แพร่", "ภูเก็ต",
                    "มหาสารคาม", "มุกดาหาร", "แม่ฮ่องสอน", "ยโสธร", "ยะลา", "ร้อยเอ็ด", "ระนอง", "ระยอง", "ราชบุรี",
                    "ลพบุรี", "ลำปาง", "ลำพูน", "เลย", "ศรีสะเกษ", "สกลนคร", "สงขลา", "สตูล", "สมุทรปราการ",
                    "สมุทรสงคราม", "สมุทรสาคร", "สระแก้ว", "สระบุรี", "สิงห์บุรี", "สุโขทัย", "สุพรรณบุรี",
                    "สุราษฎร์ธานี", "สุรินทร์", "หนองคาย", "หนองบัวลำภู", "อยุธยา", "อ่างทอง", "อำนาจเจริญ", "อุดรธานี",
                    "อุตรดิตถ์", "อุทัยธานี", "อุบลราชธานี").forEach(provinceName -> {
                        Province provinces = new Province();
                        provinces.setProvinceName(provinceName);
                        provinceRepository.save(provinces);
                    });
            Province province1 = provinceRepository.findByProvinceId(1L);
            Province province2 = provinceRepository.findByProvinceId(2L);
            Province province3 = provinceRepository.findByProvinceId(3L);
            Province province4 = provinceRepository.findByProvinceId(4L);
            Province province5 = provinceRepository.findByProvinceId(5L);
            Province province6 = provinceRepository.findByProvinceId(6L);

            Stream.of("ค้าขาย", "วิศวกร", "หมอ", "พยาบาล", "นักดนดรี", "ข้าราชการ", "นักการเมือง", "ครู", "ช่าง",
                    "นักเขียนโปรแกรม", "นักกีฬา", "นักกีฬา", "อื่นๆ").forEach(careerName -> {
                        Career career = new Career();
                        career.setCareerName(careerName);
                        careerRepository.save(career);
                    });
            Career career1 = careerRepository.findByCareerId(1L);
            Career career2 = careerRepository.findByCareerId(2L);
            Career career3 = careerRepository.findByCareerId(3L);
            Career career4 = careerRepository.findByCareerId(4L);
            Career career5 = careerRepository.findByCareerId(5L);
            Career career6 = careerRepository.findByCareerId(6L);

            Customer customerdb1 = new Customer();
            customerdb1.setCusId(1L);
            customerdb1.setCustomerIDs("C1");
            customerdb1.setCustomerName("Sunvo");
            customerdb1.setCustomerPassword("123456");
            customerdb1.setCustomerAddress("Korat");
            customerdb1.setCustomerPhone("0987654321");
            customerdb1.setCustomerGender("Man");
            customerdb1.setCustomerBirthday(dateB1c);
            customerdb1.setCareer(career1);
            customerdb1.setProvince(province1);
            customerRepository.save(customerdb1);

            Customer customerdb2 = new Customer();
            customerdb2.setCusId(2L);
            customerdb2.setCustomerIDs("C2");
            customerdb2.setCustomerName("Ploy");
            customerdb2.setCustomerPassword("123456");
            customerdb2.setCustomerAddress("Suranaree");
            customerdb2.setCustomerPhone("0987654321");
            customerdb2.setCustomerGender("Man");
            customerdb2.setCustomerBirthday(dateB2c);
            customerdb2.setCareer(career2);
            customerdb2.setProvince(province2);
            customerRepository.save(customerdb2);

            Customer customerdb3 = new Customer();
            customerdb3.setCusId(3L);
            customerdb3.setCustomerIDs("C3");
            customerdb3.setCustomerName("Ao");
            customerdb3.setCustomerPassword("123456");
            customerdb3.setCustomerAddress("Sungneon");
            customerdb3.setCustomerPhone("0987654321");
            customerdb3.setCustomerGender("Man");
            customerdb3.setCustomerBirthday(dateB1c);
            customerdb3.setCareer(career3);
            customerdb3.setProvince(province3);
            customerRepository.save(customerdb3);

            Customer customerdb4 = new Customer();
            customerdb4.setCusId(4L);
            customerdb4.setCustomerIDs("C4");
            customerdb4.setCustomerName("Wahn");
            customerdb4.setCustomerPassword("123456");
            customerdb4.setCustomerAddress("Krathok");
            customerdb4.setCustomerPhone("0987654321");
            customerdb4.setCustomerGender("Man");
            customerdb4.setCustomerBirthday(dateB2c);
            customerdb4.setCareer(career4);
            customerdb4.setProvince(province4);
            customerRepository.save(customerdb4);

            Customer customerdb5 = new Customer();
            customerdb5.setCusId(5L);
            customerdb5.setCustomerIDs("C5");
            customerdb5.setCustomerName("Opal");
            customerdb5.setCustomerPassword("123456");
            customerdb5.setCustomerAddress("Khonkan");
            customerdb5.setCustomerPhone("0987654321");
            customerdb5.setCustomerGender("Man");
            customerdb5.setCustomerBirthday(dateB1c);
            customerdb5.setCareer(career5);
            customerdb5.setProvince(province5);
            customerRepository.save(customerdb5);

            Customer customerdb6 = new Customer();
            customerdb6.setCusId(6L);
            customerdb6.setCustomerIDs("C6");
            customerdb6.setCustomerName("Meen");
            customerdb6.setCustomerPassword("123456");
            customerdb6.setCustomerAddress("Korat");
            customerdb6.setCustomerPhone("0987654321");
            customerdb6.setCustomerGender("Man");
            customerdb6.setCustomerBirthday(dateB2c);
            customerdb6.setCareer(career6);
            customerdb6.setProvince(province6);
            customerRepository.save(customerdb6);

            Customer c1 = customerRepository.findByCusId(1L);
            Customer c2 = customerRepository.findByCusId(2L);
            Customer c3 = customerRepository.findByCusId(3L);
            Customer c4 = customerRepository.findByCusId(4L);
            Customer c5 = customerRepository.findByCusId(5L);
            Customer c6 = customerRepository.findByCusId(6L);

                            Style styledb1 = new Style();
                            styledb1.setStyleID(1L);
                            styledb1.setStyleIDs("Sy1");
                            styledb1.setStyleName("korea style");
                            styledb1.setStylePrice(3000);
                            styleRepository.save(styledb1);

                            Style styledb2 = new Style();
                            styledb2.setStyleID(2L);
                            styledb2.setStyleIDs("Sy2");
                            styledb2.setStyleName("thai style");
                            styledb2.setStylePrice(2000);
                            styleRepository.save(styledb2);

                            Style styledb3 = new Style();
                            styledb3.setStyleID(3L);
                            styledb3.setStyleIDs("Sy3");
                            styledb3.setStyleName("laos style");
                            styledb3.setStylePrice(1700);
                            styleRepository.save(styledb3);

                            Style styledb4 = new Style();
                            styledb4.setStyleID(4L);
                            styledb4.setStyleIDs("Sy4");
                            styledb4.setStyleName("wedding");
                            styledb4.setStylePrice(5000);
                            styleRepository.save(styledb4);

                            Style styledb5 = new Style();
                            styledb5.setStyleID(5L);
                            styledb5.setStyleIDs("Sy5");
                            styledb5.setStyleName("thai wedding");
                            styledb5.setStylePrice(4500);
                            styleRepository.save(styledb5);

                            Style styledb6 = new Style();
                            styledb6.setStyleID(6L);
                            styledb6.setStyleIDs("Sy6");
                            styledb6.setStyleName("chinese style");
                            styledb6.setStylePrice(2500);
                            styleRepository.save(styledb6);
                       
            Style sy1 = styleRepository.findBystyleID(1L);
            Style sy2 = styleRepository.findBystyleID(2L);
            Style sy3 = styleRepository.findBystyleID(3L);
            Style sy4 = styleRepository.findBystyleID(4L);
            Style sy5 = styleRepository.findBystyleID(5L);
            Style sy6 = styleRepository.findBystyleID(6L);

            Booking bookingdb = new Booking();
            String bDate1 = "20:04:1998";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate bdate = LocalDate.parse(bDate1, formatter);
            bookingdb.setBookingId(1L);
            bookingdb.setBookingDate(bdate);
            bookingdb.setStatus("not paid");
            bookingdb.setStatusBooking("Booking");
            bookingdb.setCategory("งานวันเกิด");
            bookingdb.setStaff(st1);
            bookingdb.setCustomer(c3);
            bookingdb.setStyle(sy1);
            bookingRepository.save(bookingdb);
            
            Booking b1 = bookingRepository.findByBookingId(1L);

            Stream.of("การบริการ", "ราคา", "ธุระส่วนตัวของลูกค้า").forEach(typeReasonName -> {
                TypeReason typeReasondb = new TypeReason();
                typeReasondb.setTypeReasonName(typeReasonName);
                if(typeReasonName=="การบริการ"){
                    typeReasondb.setTypeReasonID(1L);
                    typeReasonRepository.save(typeReasondb);
                }else if(typeReasonName=="ราคา"){
                    typeReasondb.setTypeReasonID(2L);
                    typeReasonRepository.save(typeReasondb);
                }else if(typeReasonName=="ธุระส่วนตัวของลูกค้า"){
                    typeReasondb.setTypeReasonID(3L);
                    typeReasonRepository.save(typeReasondb);
            }
            });

            


            Selling sellingdb = new Selling();
            String sDate2 = "20:04:1998";
            DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate sdate = LocalDate.parse(sDate2, formatter2);
            sellingdb.setSellingId(1L);
            sellingdb.setSellingDate(sdate);
            sellingdb.setCustomer(c2);
            sellingdb.setStaff(st1);
            sellingdb.setStatus("not paid");
            sellingdb.setProduct(pt1);
            sellingdb.setCommentSelling("ร้านชุดสวย");
            sellingRepository.save(sellingdb);

            Selling sellingdb2 = new Selling();
            sellingdb2.setSellingId(2L);
            sellingdb2.setSellingDate(sdate);
            sellingdb2.setCustomer(c6);
            sellingdb2.setStaff(st1);
            sellingdb2.setStatus("paid");
            sellingdb2.setProduct(pt1);
            sellingdb2.setCommentSelling("ร้านชุดสวยอิ");
            sellingRepository.save(sellingdb2);

            Selling sellingdb3 = new Selling();
            sellingdb3.setSellingId(3L);
            sellingdb3.setSellingDate(sdate);
            sellingdb3.setCustomer(c5);
            sellingdb3.setStaff(st1);
            sellingdb3.setStatus("paid");
            sellingdb3.setProduct(pt1);
            sellingdb3.setCommentSelling("ร้านชุดอิ");
            sellingRepository.save(sellingdb3);


            Selling sl1 = sellingRepository.findBySellingId(1L);
            Selling sl2 = sellingRepository.findBySellingId(2L);



                Stream.of("มาก", "ปานกลาง","น้อย").forEach(levelReview -> {
                LevelReview level = new LevelReview();
                level.setLevelName(levelReview);
                if(levelReview=="มาก"){
                    level.setLevelReviewId(1L);
                    levelReviewRepository.save(level);
                }else if(levelReview=="ปานกลาง"){
                    level.setLevelReviewId(2L);
                    levelReviewRepository.save(level);
                }else if(levelReview=="น้อย"){
                    level.setLevelReviewId(3L);
                    levelReviewRepository.save(level);
            }
            });
            
            TypeReason tr1 = typeReasonRepository.findByTypeReasonID(1L);

            BookingCancle bookingCancledb = new BookingCancle();
            Date bookingCancleDate = new Date();
            bookingCancledb.setBookingCancleID(1L);
            bookingCancledb.setBookingCancleIDs("Bc1");
            bookingCancledb.setBookingCancleDate(bookingCancleDate);
            bookingCancledb.setBookingCancleStatus("cancled");
            bookingCancledb.setBookingCancleReason("BecauseAA");
            bookingCancledb.setBooking(b1);
            bookingCancledb.setTypeReason(tr1);
            bookingCancleRepository.save(bookingCancledb); 


            Stream.of("คืนสินค้าครบ", "คืนสินค้าไม่ครบ").forEach(restoreType -> {
                RestoreType rtype = new RestoreType();
                rtype.setTypeRestoreName(restoreType);
                if(restoreType=="คืนสินค้าครบ"){
                    rtype.setRestoreTypeId(1L);
                    restoreTypeRepository.save(rtype);
                }else if(restoreType=="คืนสินค้าไม่ครบ"){
                    rtype.setRestoreTypeId(2L);
                    restoreTypeRepository.save(rtype);
                }
            });
        

            Lease leasedb2 = new Lease();
            String l2Date1 = "07:07:1998";
            String l2Date2 = "06:07:1998";
            DateTimeFormatter l2formatter = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate l2date = LocalDate.parse(l2Date1, l2formatter);
            LocalDate l2date1 = LocalDate.parse(l2Date2, l2formatter);
            leasedb2.setCustomer(c3);
            leasedb2.setDateStart(l2date);
            leasedb2.setDateEnd(l2date1);
            leasedb2.setStatus("not paid");
            leasedb2.setLeaseStatus("Rent");
            leasedb2.setCommentRenting("ชุดสวยมาก");
            leasedb2.setStaff(st1);
            leasedb2.setProduct(pt1);
            leaseRepository.save(leasedb2);

            Lease leasedb3 = new Lease();
            String l2Date12 = "07:07:1998";
            String l2Date22 = "06:07:1998";
            DateTimeFormatter l2formatter2 = DateTimeFormatter.ofPattern("dd:MM:yyyy");
            LocalDate l2date2 = LocalDate.parse(l2Date12, l2formatter2);
            LocalDate l2date12 = LocalDate.parse(l2Date22, l2formatter2);
            leasedb3.setCustomer(c2);
            leasedb3.setDateStart(l2date2);
            leasedb3.setDateEnd(l2date12);
            leasedb3.setStatus("paid");
            leasedb3.setLeaseStatus("Rent");
            leasedb3.setCommentRenting("ชุดสวย");
            leasedb3.setStaff(st1);
            leasedb3.setProduct(pt1);
            leaseRepository.save(leasedb3);

            Lease leasedb4 = new Lease();
            leasedb4.setCustomer(c6);
            leasedb4.setDateStart(l2date2);
            leasedb4.setDateEnd(l2date12);
            leasedb4.setStatus("paid");
            leasedb4.setLeaseStatus("Rent");
            leasedb4.setCommentRenting("ชุดสวยอิ");
            leasedb4.setStaff(st1);
            leasedb4.setProduct(pt1);
            leaseRepository.save(leasedb4);

            Lease lid = leaseRepository.findByLeaseId(1L);
            Lease lid2 = leaseRepository.findByLeaseId(2L);

            Restore restoredb = new Restore();
            Date dateRestore = new Date();
            restoredb.setDateRestore(dateRestore);
            restoredb.setStatusRestore("restore");
            restoredb.setCommentRestore("ชุดชำรุด");
            restoredb.setLease(lid);
            RestoreType RT1 = restoreTypeRepository.findByRestoreTypeId(1L);
            restoredb.setRestoreType(RT1);
            restoreRepository.save(restoredb);

            PayMent paymentdb = new PayMent();
            Date paydate = new Date();
            paymentdb.setPmId(1L);
            paymentdb.setBillPayment("BillPayment1");
            paymentdb.setTypePay("Renting");
            paymentdb.setStatusPay("paid");
            paymentdb.setCustomer(c1);
            paymentdb.setDatePay(paydate);
            paymentdb.setLease(lid);
            payMentRepository.save(paymentdb);

          
            System.out.println("\n Spring-Boot Complete");
        };
    }
}