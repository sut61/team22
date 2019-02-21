import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { MatSort } from '@angular/material';
import { CancleService } from '../service/cancle.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material';
import { isNull } from '@angular/compiler/src/output/output_ast';

@Component({
  selector: 'app-cancle',
  templateUrl: './cancle.component.html',
  styleUrls: ['./cancle.component.css']
})
export class CancleComponent implements OnInit {
  bookingColumns: string[] = ['customerIDs', 'bookingId', 'staffName', 'styleName', 'bookingDate', 'statusBooking', 'category'];
  bookingCancleColumn1: string[] = ['bookingId', 'bookingCancleID', 'bookingCancleDate', 'bookingCancleReason',
    'statusBooking', 'typeReasonName'];


  customerIDs: Array<any>;
  Bookings: Array<any>;
  bookingDate: Array<any>;
  category: Array<any>;
  bookingId: Array<any>;
  statusBooking: Array<any>;

  TypeReasons: Array<any>;
  typeReasonName: Array<any>;
  typeReasonID: Array<any>;


  BookingCancle: Array<any>;
  BookingCancles: Array<any>;
  bookingCancleID: Array<any>;
  bookingCancleDate: Array<any>;
  bookingCancleReason: Array<any>;
  bookingCancleStatus: Array<any>;

  PayMents: Array<any>;
  pmId: Array<any>;
  datePay: Array<any>;
  statusPay: Array<any>;
  typePay: Array<any>;

  views: any = {
    customerIDs: '',
    bookingId: '',
    staffName: '',
    styleName: '',
    bookingDate: '',
    statusBooking: '',
    category: '',
    bookingCancleReason: '',
    typeReasonName: '',
    BookingCancles: ''
  };

  pipe = new DatePipe('en-US');
  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private cancleservice: CancleService, private httpClient: HttpClient, private router: Router, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.cancleservice.getBookingCancle().subscribe(data => {
      this.BookingCancles = data;
      console.log(this.BookingCancles);
    });
    this.cancleservice.getBookingCancle().subscribe(data => {
      this.BookingCancle = data;
      console.log(this.BookingCancle);
    });
    this.cancleservice.getBooking().subscribe(data => {
      this.Bookings = data;
      console.log(this.Bookings);
    });
    this.cancleservice.getTypeReason().subscribe(data => {
      this.TypeReasons = data;
      console.log(this.TypeReasons);
    });
  }


  OK() {
    const rex = new RegExp('[Because].+[a-z]{8,20}');
    console.log(this.views.bookingCancleReason
      , this.views.selectBookingId,
      this.views.typeReasonName,
      this.views.BookingCancles,
      this.views.selectCustomerIDs,
      this.views.selectBookingId,
      this.views.selectStyleName,
      this.views.selectBookingDate,
      this.views.selectStatusBooking,
      this.views.selectCategory,
      this.views.selectStaffName
    );

    if (this.views.bookingCancleReason === ''
      || this.views.typeReasonName === '' ||
      this.views.selectBookingId === '' ||
      this.views.selectCustomerIDs === '' ||
      this.views.selectStyleName === '' ||
      this.views.selectBookingDate === '' ||
      this.views.selectStatusBooking === '' ||
      this.views.selectStaffName === '') {
        this.snackBar.open('กรุณาใส่ข้อมูลให้ครบ');
    } else {
      if (this.views.bookingCancleReason != null) {
        if (rex.test(this.views.bookingCancleReason)) {

          this.cancleservice
            .CheckbookingCancleReason(this.views.bookingCancleReason)
            .subscribe(checkbookingCancleReason => {
              console.log(checkbookingCancleReason);
              if (checkbookingCancleReason != null) {
                this.snackBar.open('เหตุผลที่ยกเลิกซ้ำ ', 'ตกลง', {});
              } else {
                this.httpClient.post('http://localhost:8080/bookingCanclecon/' +
                  this.views.selectBookingId + '/' +
                  this.views.bookingCancleReason  + '/' +
                  this.views.typeReasonName
                  , this.views.BookingCancles, this.views)
                  .subscribe(
                    data => {
                      console.log('POST Request is successful', data);
                      this.snackBar.open('บันทึกข้อมูลสำเร็จ ', 'สำเร็จ', {
                      });
                    },
                  );
              }
            });
        } else {
          this.snackBar.open('กรุณากรอกข้อมูลเหตุผลที่ยกเลิก 8 ตัวขึ้นไปและขึ้นต้นด้วยคำว่า Because');
        }
      } else {
        this.snackBar.open('กรุณาใส่ข้อมูลให้ครบ');
      }
    }
  }
  UPDATE() {
    this.httpClient.put('http://localhost:8080/cancleStatus/' + this.bookingCancleID + '/' +
      this.views.selectBookingId + '/' + 'Cancled', this.BookingCancles)
      .subscribe(
        data => {
          if (data) {
            console.log('put Request is successful', data);
            this.snackBar.open('อัพเดทข้อมูลสำเร็จ ', 'OK', {
            });
          }
        },
        error => {
          this.snackBar.open('อัพเดทข้อมูลไม่สำเร็จ', 'OK', {
          });
          console.log('error', error);
        });
  }
  selectRowBooking(row) {
    this.views.selectCustomerIDs = row.customer.customerIDs;
    this.views.selectBookingId = row.bookingId;
    this.views.selectStyleName = row.style.styleName;
    this.views.selectStaffName = row.staff.staffName;
    this.views.selectBookingDate = row.bookingDate;
    this.views.selectStatusBooking = row.statusBooking;
    this.views.selectCategory = row.category;
    console.log(this.views.selectCustomerIDs);
    console.log(this.views.selectBookingId);
    console.log(this.views.selectStyleName);
    console.log(this.views.selectStaffName);
    console.log(this.views.selectBookingDate);
    console.log(this.views.selectStatusBooking);
    console.log(this.views.selectCategory);
  }
  getUrl() {
    return "url('https://images.pexels.com/photos/1470165/pexels-photo-1470165.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=650&w=940')";
  }
}

