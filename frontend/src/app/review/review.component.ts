import { Component, OnInit, ViewChild } from '@angular/core';
import { ReviewService } from '../service/review.service';
import { MatSort } from '@angular/material';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.css']
})
export class ReviewComponent implements OnInit {
  displayedColumns1: string[] = [
    'levelName',
    'reviewComment',
    'ment2',
    'reviewDate'
  ];
  displayedColumns2: String[] = [
    'productID',
    'productName',
    'status',
    'customerName'
  ];

  SellingReview: Array<any>;
  Reviews: Array<any>;
  levelName: Array<any>;
  reviewDate: Array<any>;
  pipe = new DatePipe('en-US');
  reviewComment: Array<any>;
  ment2: Array<any>;
  Customers: Array<any>;
  customerIDs: Array<any>;
  customerName: Array<any>;
  productID: Array<any>;
  productName: Array<any>;
  status: Array<any>;

  selling: Array<any>;
  LevelReviews: Array<any>;

  views: any = {
    productID: '',
    productName: '',
    status: '',
    customerName: '',
    selectProductID: '',
    selectProductName: '',
    selectStatus: '',
    selectCustomerName: '',
    selectSellingId: '',
    reviewComment: '',
    ment2: '',
    levelName: '',
    reviewDate: ''
  };

  @ViewChild(MatSort)
  sort: MatSort;
  Selling: any;
  constructor(
    private reviewService: ReviewService,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router
  ) {}

  ngOnInit() {
    this.reviewService.getCustomer().subscribe(data => {
      this.Customers = data;
      console.log(this.Customers);
    });
    this.reviewService.getReview().subscribe(data => {
      this.Reviews = data;
      console.log(this.Reviews);
    });
    this.reviewService.getSelling().subscribe(data => {
      this.Selling = data;
      console.log(this.Selling);
    });
    this.reviewService.getLevelReview().subscribe(data => {
      this.LevelReviews = data;
      console.log(this.LevelReviews);
    });
  }
  save() {
    const rex = new RegExp('[ควร].+[กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮฯะัาำิีึืฺุูเแโใไๅๆ็่้๊๋์]{5,20}');
    console.log(
      this.views.selectProductID,
      this.views.selectProductName,
      this.views.selectStatus,
      this.views.selectCustomerName,
      this.views.levelName,
      this.views.reviewDate,
      this.views.reviewComment,
      this.views.ment2
    );

    if (
      this.views.selectProductID === '' ||
      this.views.selectProductName === '' ||
      this.views.selectStatus === '' ||
      this.views.selectCustomerName === '' ||
      this.views.levelName === '' ||
      this.views.reviewDate === '' ||
      this.views.reviewComment === '' ||
      this.views.ment2 === ''
    ) {
      this.snackBar.open(
        'กรุณากรอกข้อมูลให้ครบ' ,'uncomplete', {}
      );
    } else {
      if (this.views.reviewComment != null) {
        if (rex.test(this.views.reviewComment)) {
          this.reviewService
            .CheckReviewComment(this.views.reviewComment)
            .subscribe(CheckReviewComment => {
              console.log(CheckReviewComment);
              if (CheckReviewComment != null) {
                this.snackBar.open('คอมเม้นซ้ำ ', 'ตกลง', {});
              } else {
                this.httpClient
                  .post(
                    'http://localhost:8080/Rev/' +
                      this.views.selectProductID +
                      '/' +
                      this.views.selectProductName +
                      '/' +
                      this.views.selectStatus +
                      '/' +
                      this.views.selectCustomerName +
                      '/' +
                      this.views.levelName +
                      '/' +
                      this.pipe.transform(this.views.reviewDate, 'dd:MM:yyyy') +
                      '/' +
                      this.views.reviewComment +
                      '/' +
                      this.views.ment2,
                    this.Reviews
                  )
                  .subscribe(
                    data => {
                      console.log('POST Request is successful', data);
                      this.snackBar.open('input detail ', 'complete', {});
                    },
                    error => {
                      this.snackBar.open('input detail ', 'uncomplete', {});
                      console.log('Error', error);
                    }
                  );
                this.httpClient
                  .put(
                    'http://localhost:8080/sellingRe/' +
                      this.views.selectStatus +
                      '/' +
                      'review',
                    this.selling
                  )
                  .subscribe(
                    data => {
                      if (data) {
                        console.log('Success');
                      }
                    },
                    error => {
                      console.log('error', error);
                    }
                  );
              }
            });
        } else {
          this.snackBar.open(
            'กรุณากรอกข้อมูล Comment 5 ถึง 20 ตัวและขึ้นต้นด้วยคำว่าควร', 'uncomplete', {}
          );
        }
      }
    }
  }
  selectRow(row) {
    this.views.selectProductID = row.product.productIds;
    this.views.selectProductName = row.product.productName;
    this.views.selectStatus = row.sellingId;
    this.views.selectCustomerName = row.customer.customerName;
    console.log(this.views.selectProductID);
    console.log(this.views.selectProductName);
    console.log(this.views.selectStatus);
    console.log(this.views.selectCustomerName);
  }
}
