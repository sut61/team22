import { Component, OnInit, ViewChild } from '@angular/core';
import { LeaseService } from '../service/lease.service';
import { MatSort } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material';
import { isNull } from '@angular/compiler/src/output/output_ast';


@Component({
  selector: 'app-renting',
  templateUrl: './renting.component.html',
  styleUrls: ['./renting.component.css']

})
export class RentingComponent implements OnInit {

  displayedColumns1: string[] = ['productID', 'productName', 'productPrice', 'productStatus'];
  displayedColumns2: string[] = ['staffID', 'staffName', 'position'];
  Staffs: Array<any>;
  Product: Array<any>;
  customerID: Array<any>;
  ReserveDate: Array<any>;
  ReturnDate: Array<any>;
  Customers: Array<any>;
  Leases: Array<any>;
  addressCustomer: Array<any>;
  staffIDs: Array<any>;
  productName: Array<any>;
  productID: Array<any>;
  productPrice: Array<any>;
  statusProduct: Array<any>;
  commentRenting: Array<any>;


  views = {
    productID: '',
    productName: '',
    productPrice: '',
    selectProductID: '',
    selectProductName: '',
    selectProductPrice: '',
    commentRenting: '',
  };

  pipe = new DatePipe('en-US');

  @ViewChild(MatSort)
  sort: MatSort;

  constructor(private leaseservice: LeaseService, private httpClient: HttpClient, private router: Router, private snackBar: MatSnackBar) {

  }

  ngOnInit() {
    this.leaseservice.getLease().subscribe(data => {
      this.Leases = data;
      console.log(this.Leases);
    });
    this.leaseservice.getCustomer().subscribe(data => {
      this.Customers = data;
      console.log(this.Customers);
    });
    this.leaseservice.getStaff().subscribe(data => {
      this.Staffs = data;
      console.log(this.Staffs);
    });
    this.leaseservice.getProduct().subscribe(data => {
      this.Product = data;
      console.log(this.Product);
    });
  }

  save() {
    const rex = new RegExp('[ชุด].+[กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮฯะัาำิีึืฺุูเแโใไๅๆ็่้๊๋์]{5,20}');
    console.log(this.views.commentRenting);

    if (this.views.selectProductID == null || this.views.selectProductName == null || this.views.selectProductPrice == null
      || this.customerID == null || this.addressCustomer == null || this.ReserveDate == null || this.ReturnDate == null
      || this.staffIDs == null || this.views.commentRenting == null) {
        this.snackBar.open('กรุณากรอกข้อมูลให้ครบ','uncomplete',{});
    } else {
      if (this.views.commentRenting != null) {

        if (rex.test(this.views.commentRenting)) {

          this.leaseservice
          .CheckCommentRenting(this.views.commentRenting)
          .subscribe(checkCommentRenting => {
            console.log(checkCommentRenting);
            if (checkCommentRenting != null) {
              this.snackBar.open('คอมเม้นซ้ำ ' , 'ตกลง', {});
            } else {
          this.httpClient.post('http://localhost:8080/renting/' + this.views.selectProductID + '/'
          + this.views.selectProductName + '/' + this.views.selectProductPrice + '/'
          + this.customerID + '/' + this.staffIDs + '/' +
          this.pipe.transform(this.ReserveDate, 'dd:MM:yyyy') + '/'
          + this.pipe.transform(this.ReturnDate, 'dd:MM:yyyy') + '/' + this.views.commentRenting, this.Leases)
          .subscribe(
            data => {
              console.log('POST Request is successful', data);
              this.snackBar.open('input detail ', 'complete', {
              });
            },
            error => {
              this.snackBar.open('input detail ', 'uncomplete', {
              });
              console.log('Error', error);
            }
          );
          }
        });
        } else {
          this.snackBar.open('กรุณากรอกข้อมูลComment5-20ตัวและขึ้นต้นด้วยคำว่าชุด','uncomplete',{});

        }
      }
     }  
}
  selectRow(row) {
    this.views.selectProductID = row.productIds;
    this.views.selectProductName = row.productName;
    this.views.selectProductPrice = row.productPrice;
    console.log(this.views.selectProductID);
    console.log(this.views.selectProductName);
    console.log(this.views.selectProductPrice);
  }
}

