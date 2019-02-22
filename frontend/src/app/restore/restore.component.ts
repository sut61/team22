import { Component, OnInit, ViewChild } from '@angular/core';
import { RestoreService } from '../service/restore.service';
import { MatSort } from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { DatePipe } from '@angular/common';
import { MatSnackBar } from '@angular/material';

@Component({
  selector: 'app-restore',
  templateUrl: './restore.component.html',
  styleUrls: ['./restore.component.css']
})
export class RestoreComponent implements OnInit {
  displayedColumns1: string[] = [
    'customerIDs',
    'customerName',
    'leaseId',
    'productName',
    'reserveDate',
    'returnDate',
    'leaseStatus',
    'status'
  ];
  displayedColumns3: string[] = [
    'restoreId',
    'commentRestore',
    'statusRestore',
    'customerName',
    'typeRestoreName'
  ];

  CurrentDate = new Date();
  lease: Array<any>;
  Lease: Array<any>;
  restore: Array<any>;
  restoreId: Array<any>;
  reserveDate: Array<any>;
  returnDate: Array<any>;
  Product: Array<any>;
  productName: Array<any>;
  productStatus: Array<any>;
  customerIDs: Array<any>;
  customer: Array<any>;
  status: Array<any>;
  Restore: Array<any>;
  dateRestore: Array<any>;
  statusRestore: Array<any>;
  commentRestore: Array<any>;
  PayMents: Array<any>;
  pmId: Array<any>;
  datePay: Array<any>;
  typePay: Array<any>;
  customerName: Array<any>;
  History: Array<any>;
  RestoreTypes: Array<any>;
  typeRestoreName: Array<any>;

  views: any = {
    customerIDs: '',
    leaseId: '',
    productName: '',
    customerName: '',
    selectCustomerIDs: '',
    selectLeaseId: '',
    selectProductName: '',
    selectCustomerName: '',
    commentRestore: '',
    restoreId: ''
  };

  pipe = new DatePipe('en-US');

  @ViewChild(MatSort)
  sort: MatSort;

  constructor(
    private restoreservice: RestoreService,
    private httpClient: HttpClient,
    private router: Router,
    private snackBar: MatSnackBar
  ) {}

  ngOnInit() {
    this.restoreservice.getRestore().subscribe(data => {
      this.restore = data;
      console.log(this.restore);
    });
    this.restoreservice.getLease().subscribe(data => {
      this.Lease = data;
      console.log(this.Lease);
    });
    this.restoreservice.getRestoreType().subscribe(data => {
      this.RestoreTypes = data;
      console.log(this.RestoreTypes);
    });
  }

  save() {
    const rex = new RegExp('[ชุด].+[กขฃคฅฆงจฉชซฌญฎฏฐฑฒณดตถทธนบปผฝพฟภมยรฤลฦวศษสหฬอฮฯะัาำิีึืฺุูเแโใไๅๆ็่้๊๋์]{5,20}');
    console.log(this.views.commentRestore);

    if (
      this.views.selectCustomerIDs == null ||
      this.views.selectLeaseId == null ||
      this.views.selectCustomerName == null ||
      this.views.commentRestore == null ||
      this.typeRestoreName == null
    ) {
      this.snackBar.open('กรุณากรอกข้อมูลให้ครบ','uncomplete',{});

    } else {
      if (this.views.commentRestore != null) {
        if (rex.test(this.views.commentRestore)) {
          this.restoreservice
            .CheckCommentRestore(this.views.commentRestore)
            .subscribe(CheckCommentRestore => {
              console.log(CheckCommentRestore);
              if (CheckCommentRestore != null) {
                this.snackBar.open('คอมเม้นซ้ำ ', 'uncomplete', {});
              } else {
                this.httpClient
                  .post(
                    'http://localhost:8080/restore1/' +
                      this.views.selectCustomerIDs +
                      '/' +
                      this.views.selectCustomerName +
                      '/' +
                      this.views.selectLeaseId +
                      '/' +
                      this.views.selectProductName +
                      '/' +
                      this.views.commentRestore +
                      '/' +
                      this.typeRestoreName,
                    this.Restore
                  )
                  .subscribe(
                    data => {
                      console.log('POST Request is successful', data);
                      this.snackBar.open('input detail ', 'complete', {});
                      this.httpClient
                  .put(
                    'http://localhost:8080/lease/' +
                      this.views.selectLeaseId +
                      '/' +
                      'restore',
                    this.lease
                  ).subscribe(
                    data => {
                      if (data) {
                        console.log('put Request is successful', data);
                        this.snackBar.open('input detail ', 'complete', {});
                      }
                    },
                    error => {
                      this.snackBar.open(
                        'input detail',
                        'uncomplete',
                        {}
                      );
                      console.log('error', error);
                    }
                  );
                    },
                    error => {
                      this.snackBar.open(
                        'input detail',
                        'uncomplete',
                        {}
                      );
                      console.log('Error', error);
                    }
                  );
                  }
            });
        } else {
          this.snackBar.open(
            'กรุณากรอกข้อมูลComment 5-20ตัวและขึ้นต้นด้วยคำว่าชุด',
            'uncomplete',{}
          );
        }
      }
    }
  }

  update() {
    if (this.views.restoreId === '') {
      this.snackBar.open('กรุณาใส่ Id Restore', 'uncomplete', {});
    } else {
      if (this.views.restoreId != null) {
        this.httpClient
          .put(
            'http://localhost:8080/restorestatus/' + this.views.restoreId,
            this.restore
          )
          .subscribe(
            dataq => {
              if (dataq) {
                console.log('Put Request is successful', dataq);
                this.snackBar.open('input detail', 'complete', {});
              }
            },
            error => {
              this.snackBar.open('input detail', 'uncomplete', {});
              console.log('error', error);
            }
          );
      } 
    }
  }

  selectRow(row) {
    this.views.selectCustomerIDs = row.customer.customerIDs;
    this.views.selectLeaseId = row.leaseId;
    this.views.selectProductName = row.product.productName;
    this.views.selectCustomerName = row.customer.customerName;
    console.log(this.views.selectCustomerIDs);
    console.log(this.views.selectLeaseId);
    console.log(this.views.selectProductName);
    console.log(this.views.selectCustomerName);
  }
  selectRow1(row) {
    this.views.restoreId = row.restoreId;
    console.log(this.views.restoreId);
  }
}
