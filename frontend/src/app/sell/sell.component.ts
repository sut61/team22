import { Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SellingService } from '../service/selling.service';
import { MatSort } from '@angular/material';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material';
@Component({
  selector: 'app-sell',
  templateUrl: './sell.component.html',
  styleUrls: ['./sell.component.css']
})
export class SellComponent implements OnInit {
  displayedColumns1: string[] = [
    'productID',
    'productName',
    'productPrice',
    'statusProduct'
  ];
  displayedColumns2: string[] = ['staffIDs', 'staffName', 'position'];
  Customers: Array<any>;
  customerID: Array<any>;

  Staffs: Array<any>;
  staffIDs: Array<any>;
  staffName: Array<any>;
  position: Array<any>;

  Sellings: Array<any>;
  sellingDate: Array<any>;
  pipe = new DatePipe('en-US');
  commentSelling: Array<any>;

  Products: Array<any>;
  productID: Array<any>;
  productName: Array<any>;
  statusProduct: Array<any>;
  productPrice: Array<any>;

  views: any = {
    productID: '',
    productName: '',
    productPrice: '',
    selectProductID: '',
    selectProductName: '',
    selectProductPrice: '',
    commentSelling: '',
    customerID: '',
    staffIDs: '',
    sellingDate: ''

  };

  @ViewChild(MatSort)
  sort: MatSort;
  constructor(
    private sellingService: SellingService,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit() {
    this.sellingService.getCustomer().subscribe(data => {
      this.Sellings = data;
      console.log(this.Sellings);
    });
    this.sellingService.getCustomer().subscribe(data => {
      this.Customers = data;
      console.log(this.Customers);
    });
    this.sellingService.getStaff().subscribe(data => {
      this.Staffs = data;
      console.log(this.Staffs);
    });
    this.sellingService.getProduct().subscribe(data => {
      this.Products = data;
      console.log(this.Products);
    });
  }
  save() {
    const rex = new RegExp(
      '[ร้าน].+[กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮฯะัาำิีึืฺุูเแโใไๅๆ็่้๊๋์]{5,20}'
    );
    console.log(
      this.views.commentSelling,
      this.views.selectProductID,
      this.views.selectProductName,
      this.views.selectProductPrice,
      this.views.customerID,
      this.views.staffIDs,
      this.views.sellingDate
    );

    if (
      this.views.selectProductID === '' ||
      this.views.selectProductName === '' ||
      this.views.selectProductPrice === '' ||
      this.views.customerID === '' ||
      this.views.staffIDs === '' ||
      this.views.sellingDate === '' ||
      this.views.commentSelling === ''
    ) {
      this.snackBar.open(
        'กรุณาเลือกข้อมูลให้ครบ','uncomplete', {}
      );
    } else {
      if (this.views.commentSelling != null) {
        if (rex.test(this.views.commentSelling)) {
          this.sellingService
            .CheckCommentSelling(this.views.commentSelling)
            .subscribe(CheckCommentSelling => {
              console.log(CheckCommentSelling);
              if (CheckCommentSelling != null) {
                this.snackBar.open('คอมเม้นซ้ำ ', 'ตกลง', {});
              } else {
                this.httpClient
                  .post(
                    'http://localhost:8080/sell/' +
                      this.views.selectProductID +
                      '/' +
                      this.views.selectProductName +
                      '/' +
                      this.views.selectProductPrice +
                      '/' +
                      this.views.customerID +
                      '/' +
                      this.views.staffIDs +
                      '/' +
                      this.pipe.transform(this.views.sellingDate, 'dd:MM:yyyy') +
                      '/' +
                      this.views.commentSelling,
                    this.Sellings
                  )
                  .subscribe(
                    data => {
                      console.log('POST Request is successful', data);
                      this.snackBar.open('input detail ', 'complete', {});
                    },
                    error => {
                      this.snackBar.open('input not detail ', 'uncomplete', {});
                      console.log('Error', error);
                    }
                  );
              }
            });
        } else {
          this.snackBar.open(
            'กรุณากรอกข้อมูล Comment 5 ถึง 20 ตัวและขึ้นต้นด้วยคำว่าร้าน','uncomplete', {}
          );
        }
        } else {
          this.snackBar.open(
            'กรุณากรอกข้อมูล Comment 5 ถึง 20 ตัวและขึ้นต้นด้วยคำว่าร้าน','uncomplete', {}
          );
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
