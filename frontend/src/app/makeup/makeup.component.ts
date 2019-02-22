import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import { MatSort, MatSnackBar } from '@angular/material';
import { MakeupService } from '../service/makeup.service';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-makeup',
  templateUrl: './makeup.component.html',
  styleUrls: ['./makeup.component.css']
})
export class MakeupComponent implements OnInit {
  displayedColumns2: string[] = ['stID', 'staffIDs', 'staffName', 'positionName'];
  displayedColumns1: string[] = ['styleID', 'styleIDs', 'styleName', 'stylePrice'];

  Bookings: Array<any>;
  Customers: Array<any>;
  Styles: Array<any>;
  Staffs: Array<any>;
  Positions: Array<any>;

  bookingDate: Array<any>;
  category: Array<any>;
  cusId: Array<any>;
  customerIDs: Array<any>;
  customerName: Array<any>;
  customerAddress: Array<any>;

  stID: Array<any>;
  staffIDs: Array<any>;
  staffName: Array<any>;
  positionName: Array<any>;

  styleIDs: Array<any>;
  styleID: Array<any>;
  styleName: Array<any>;
  stylePrice: Array<any>;

  views: any = {
    category: '',
    cusId: '',
    customerIDs: '',
    customerAddress: '',
    customerName: '',
    bookingDate: ''
  }

  viewStyle: any = {
    styleIDs: '',
    styleID: '',
    styleName: '',
    stylePrice: ''
  };

  viewStaff: any = {
    stID: '',
    staffIDs: '',
    staffName: '',
    positionName: ''
  };

  pipe = new DatePipe('en-US');
  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private makeupservice: MakeupService, private httpClient: HttpClient, private router: Router, private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.makeupservice.getBooking().subscribe(data => {
      this.Bookings = data;
      console.log(this.Bookings);
    });
    this.makeupservice.getCustomer().subscribe(data => {
      this.Customers = data;
      console.log(this.Customers);
    });
    this.makeupservice.getStaff().subscribe(data => {
      this.Staffs = data;
      console.log(this.Staffs);
    });
    this.makeupservice.getStyle().subscribe(data => {
      this.Styles = data;
      console.log(this.Styles);
    });
    this.makeupservice.getPosition().subscribe(data => {
      this.Positions = data;
      console.log(this.Positions);

    });
  }

  OK() {
    const rex = new RegExp('[งาน].+[กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮฯะัาำิีึืฺุูเแโใไๅๆ็่้๊๋์]{4,50}');
    console.log(this.views.category);
    console.log(this.viewStyle.selectstyleID,
    this.viewStyle.selectstyleIDs,
    this.viewStyle.selectstyleName,
    this.viewStyle.selectstylePrice,
    this.viewStaff.selectstID,
    this.viewStaff.selectstaffIDs,
    this.viewStaff.selectstaffIDs,
    this.pipe.transform(this.views.bookingDate, 'dd:MM:yyyy'),
    this.views.category, this.views.cusId , this.views.customerIDs,
    this.views.customerName, this.views.customerAddress
    );

    if (this.viewStyle.selectstyleID === '' ||
      this.viewStyle.selectstyleIDs === '' ||
      this.viewStyle.selectstyleName === '' ||
      this.viewStyle.selectstylePrice === '' ||
      this.viewStaff.selectstID === '' ||
      this.viewStaff.selectstaffIDs === '' ||
      this.viewStaff.selectstaff์Name === '' ||
      this.pipe.transform(this.views.bookingDate, 'dd:MM:yyyy') === '' ||
      this.views.category === '' || this.views.cusId === '' || this.views.customerIDs === '' ||
      this.views.customerName === '' || this.views.customerAddress === '' ) {
        this.snackBar.open('กรุณาใส่ข้อมูลให้ครบ');
    } else {
      if (this.views.category != null) {
        if (rex.test(this.views.category)) {
          this.makeupservice
            .Checkcategory(this.views.category)
            .subscribe(Checkcategory => {
              console.log(Checkcategory);
              if (Checkcategory != null) {
                this.snackBar.open('งานที่จะไปซ้ำ ', 'ตกลง', {});
              } else {
                this.httpClient.post('http://localhost:8080/makeupBooking/' +
                  this.viewStyle.selectstyleID + '/' + this.viewStyle.selectstyleIDs + '/' +
                  this.viewStyle.selectstyleName + '/' + this.viewStyle.selectstylePrice + '/' +
                  this.viewStaff.selectstID + '/' + this.viewStaff.selectstaffIDs + '/' +
                  this.viewStaff.selectstaff์Name + '/' +
                  this.pipe.transform(this.views.bookingDate, 'dd:MM:yyyy') + '/' +
                  this.views.category + '/' +
                  this.views.cusId + '/' + this.views.customerIDs + '/' +
                  this.views.customerName + '/' + this.views.customerAddress, this.Bookings)
                  .subscribe(
                    data => {
                      console.log('PUT Request is successful', data);
                      this.snackBar.open('ยืนยันการจองเรียบร้อยแล้ว');
                    },
                    error => {
                      this.snackBar.open('กรุณาใส่ข้อมูลให้ครบ');
                    }
                  );
              }
            });
        } else {
          this.snackBar.open('กรุณากรอกข้อมูลงานที่จะไป 5 ตัวขึ้นไปและขึ้นต้นด้วยคำว่า งาน');
        }
      } else {
        this.snackBar.open('กรุณากรอกข้อมูลงานที่จะไป 5 ตัวขึ้นไปและขึ้นต้นด้วยคำว่า งาน');
      }
    }
  }
  selectRowStyle(row) {
    this.viewStyle.selectstyleID = row.styleID;
    this.viewStyle.selectstyleIDs = row.styleIDs;
    this.viewStyle.selectstyleName = row.styleName;
    this.viewStyle.selectstylePrice = row.stylePrice;
    console.log(this.viewStyle.selectstyleID);
    console.log(this.viewStyle.selectstyleIDs);
    console.log(this.viewStyle.selectstyleName);
    console.log(this.viewStyle.selectstylePrice);
  }
  selectRowStaff(row) {
    this.viewStaff.selectstID = row.staffId;
    this.viewStaff.selectstaffIDs = row.staffIds;
    this.viewStaff.selectstaffName = row.staffName;
    console.log(this.viewStaff.selectstID);
    console.log(this.viewStaff.selectstaffIDs);
    console.log(this.viewStaff.selectstaffName);

  }

}

