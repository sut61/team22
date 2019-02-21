import {HttpClient} from '@angular/common/http';
import {CheckproductService} from '../service/checkproduct.service';
import {MatSnackBar} from '@angular/material';
import {Component, OnInit ,ViewChild} from '@angular/core';
import {DatePipe} from '@angular/common';
import {MatSort} from '@angular/material';
import { AmazingTimePickerService } from 'amazing-time-picker';

export interface Tile {
  cols: number;
  rows: number;
}
export interface StockElement {
  productID: String;
  productName: String;
  productPrice: number;
  productQuantity: number;
  type: {
    typeName: String;
  };
  status: {
    statusProduct: String;
  };
  productDate: Date;
}
@Component({
  selector: 'app-checkproduct',
  templateUrl: './checkproduct.component.html',
  styleUrls: ['./checkproduct.component.css']
})
export class CheckproductComponent implements OnInit {
  tiles: Tile[] = [
    {cols: 1, rows: 1, },
  ];
  tile_right: Tile[] = [
    {cols: 2, rows: 1, },
  ];
  max = 100;
  min = 0;
  step = 1;
  type: Array<any>;
  status: Array<any>;
  checking: Array<any>;
  product: Array<any>;
  checkproduct: Array<any>;
  checkDate: Array<any>;
  selectedTime: string;
  selectcheckDate: Array<any>;
  pipe = new DatePipe('en-US');
  @ViewChild(MatSort)
  sort: MatSort;
  views: any = {
    level : '',
    comment: '',
    productID: '',
    productName: '',
    productQuantity: '',
    productPrice : '',
    prodID : '',
    checkId:'',
    selectProductID: '',
    selectProductName: '',
    selectProductQuantity: '',
    selectProductPrice : '',
    selectPID: '',
    selectCheckProductComment: '',
    selectCheckProductLevel: '',
    selectCheckId:'',
    selectChecking:'',
    checkingSelect:'',
    time:''
  };
  displayedColumns: string[] = ['PID', 'productID', 'productName', 'productPrice', 'productQuantity', 'types','statuses'];
  displayedColumnss: string[] = ['PID','CID','level', 'comment','date','checking'];
  constructor(private CheckService: CheckproductService,private snackBar: MatSnackBar, private httpClient: HttpClient,private atp: AmazingTimePickerService) {
  }
  ngOnInit() {
    this.CheckService.getType().subscribe(data => {
      this.type = data;
      console.log(this.type);
    });
    this.CheckService.getStatus().subscribe(data => {
      this.status = data;
      console.log(this.status);
    });
    this.CheckService.getProduct().subscribe(data => {
      this.product = data;
      console.log(this.product);
    });
    this.CheckService.getCheckProduct().subscribe(data => {
      this.checkproduct = data;
      console.log(this.checkproduct);
    });
    this.CheckService.getChecking().subscribe(data => {
      this.checking = data;
      console.log(this.checking);
    });
  }
  open() {
    const amazingTimePicker = this.atp.open({
        time: this.selectedTime,
        theme: 'dark',
        arrowStyle: {
            background: 'red',
            color: 'white',
        }
    });
    amazingTimePicker.afterClose().subscribe(time => {
      this.selectedTime = time;
      console.log(this.selectedTime);
    });
}
   selectRow(row) {
    this.views.selectPID = row.prodId;
    this.views.selectProductID = row.productIds;
    this.views.selectProductName = row.productName;
    this.views.selectProductPrice = row.productPrice;
    this.views.selectProductQuantity = row.productQuantity;
    console.log(this.views.selectPID);
    console.log( this.views.selectProductName);
    console.log(this.views.selectProductPrice);
    console.log( this.views.selectProductQuantity);
    console.log(this.views.selectProductID);
  }
  electRow(row) {
    this.views.selectPID = row.product.prodId;
    this.views.selectCheckProductLevel = row.checkLevel;
    this.views.selectCheckProductComment = row.checkComment;
    this.views.selectCheckId = row.checkId;
    this.views.selectChecking = row.checked.checkingId;
    this.checkDate = row.checkDate;
    console.log(this.views.selectCheckId);
    console.log(this.views.selectPID);
    console.log(this.views.selectCheckProductComment);
    console.log(this.views.selectCheckProductLevel);
    console.log(this.checkDate);
    console.log(this.views.selectChecking);
  }
  save() {
    this.views.prodID = this.views.selectPID;
    const com = new RegExp('[A-Za-zw0-9d]{3,500}');
    this.views.checkingSelect = this.views.selectChecking;
    if (this.views.level === ''|| this.views.comment === ''||this.views.checkingSelect === '')
        {  this.snackBar.open('โปรดใส่ข้อมูลให้ครบ', 'OK', { });
    } else{
        if(this.views.level>100){
          {  this.snackBar.open('Not more than 100', 'OK', {});}
        } else  if(this.views.level<0){
          {  this.snackBar.open('Not Less than 0', 'OK', { });}
        } else { 
          if (com.test(this.views.comment)) {    
            this.httpClient.post('http://localhost:8080/checkproduct/' + this.views.prodID + '/' + this.views.level + '/' + this.views.comment
            +'/'+ this.pipe.transform(this.checkDate,'dd:MM:yyyy')+'/'+ this.selectedTime +'/'+ this.views.checkingSelect,
            this.views) .subscribe(
            data => {
              this.snackBar.open('Check ', 'complete', {
              });
              console.log('INPUT Request is successful', data);
            },
            error => {
              this.snackBar.open('Check ', 'uncomplete', {
              });
              console.log('Error', error);
            }
            );
          }
          else{
            this.snackBar.open('Comment is Between 3-500 ', 'OK', {
            });
          }
      }
  }
}
  editcheck() {
    this.views.prodID = this.views.selectPID;
    this.views.checkId = this.views.selectCheckId;
    if (!this.views.level) {
      this.views.level = this.views.selectCheckProductLevel;
    }
    if (!this.views.comment) {
      this.views.comment = this.views.selectCheckProductComment;
    }
    if (!this.views.checkingSelect) {
      this.views.checkingSelect = this.views.selectChecking;
    }
    console.log(this.views.prodID);
    console.log(this.views.checkId);
    console.log(this.views.level);
    console.log(this.views.comment);
    console.log(this.views.selectChecking);
    this.httpClient.put('http://localhost:8080/checkproduct/editcheck/'+ this.views.prodID + '/' + this.views.checkId + '/'
    + this.views.level + '/' + this.views.comment + '/'+ this.views.checkingSelect,
    this.views)  .subscribe(
      data => {
        if (data)  {
          this.snackBar.open('edit detail ', 'complete', {
          });
          console.log('Success');
        }
      },
      error => {
        this.snackBar.open('edit detail', 'uncomplete', {
        });
        console.log('Uncomplete',  error);
      }
    );
  }
  delete() {
    this.views.checkId = this.views.selectCheckId;
    this.httpClient.delete('http://localhost:8080/checkproduct/' + this.views.checkId )
    .subscribe(
      data => {
        this.snackBar.open('delete', 'complete', {
        });
        console.log('Delete Request is successful', data);
      },
      error => {
        this.snackBar.open('delete', 'uncomplete', {
        });
        console.log('Error', error);
      }
    );
  }
}