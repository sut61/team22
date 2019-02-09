import { Component, OnInit } from '@angular/core';
import {ViewChild} from '@angular/core';
import {MatSort} from '@angular/material';
import {CancleService} from '../service/cancle.service';
import { HttpClient} from '@angular/common/http';
import { Router} from '@angular/router';
import {DatePipe} from '@angular/common';
import {MatSnackBar} from '@angular/material';

@Component ({
  selector: 'app-cancle',
  templateUrl: './cancle.component.html',
  styleUrls: ['./cancle.component.css']
})
export class CancleComponent implements OnInit {
  bookingColumns: string[] = ['customerIDs', 'bookingId', 'staffName', 'styleName', 'bookingDate', 'statusBooking', 'category'];
  bookingCancleColumn1: string[] = ['bookingId', 'bookingCancleID', 'bookingCancleDate',
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
      category: ''
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
    this.httpClient.post('http://localhost:8080/bookingCancle/' +
    this.views.selectBookingId + '/' +
    this.bookingCancleReason + '/' +
    this.typeReasonName, this.BookingCancles)
    .subscribe(
      data => {
        console.log('POST Request is successful', data);
        this.snackBar.open('input detail ', 'ยกเลิกการจองสำเร็จ', {
        });
      },
      error => {
        this.snackBar.open('input detail ', 'ยกเลิกการจองไม่สำเร็จ', {
        });
        console.log('Error', error);
      });
}
UPDATE() { this.httpClient.put('http://localhost:8080/cancleStatus/' + this.bookingCancleID + '/' +
this.views.selectBookingId + '/' + 'Cancled', this.BookingCancles)
          .subscribe(
            data => {
              if (data) {
                console.log('put Request is successful', data);
                this.snackBar.open('input detail ', 'อัพเดทข้อมูลสำเร็จ', {
                });
              }
            },
            error => {
              this.snackBar.open('input detail ', 'อัพเดทข้อมูลไม่สำเร็จ', {
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

