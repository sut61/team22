import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {DatePipe} from '@angular/common';
import { SignupService } from '../service/signup.service';
import { Router } from '@angular/router';
import {MatDialog , MatDialogRef} from '@angular/material';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  disableSelect = new FormControl(false);
  pipe = new DatePipe('en-US');
  hide = true;
  customerRegister: Array<any>;
  cusId: Array<any>;
  customerIDs: Array<any>;
  customerPassword: Array<any>;
  customerName: Array<any>;
  customerPhone: Array<any>;
  customerGender: Array<any>;
  customerBirthday: Array<any>;
  customerAddress: Array<any>;
  career: Array<any>;
  province: Array<any>;
  customer = {
    cusId : '',
    customerIDs : '',
    customerPassword: '',
    customerName: '',
    customerPhone: '',
    customerGender: '',
    customerBirthday: '',
    customerAddress: '',
    career: '',
    province: ''
  };

  constructor(private signupService: SignupService, private httpClient: HttpClient, private router: Router , public dialog: MatDialog) { }

  ngOnInit() {
    this.signupService.getCareer().subscribe(data => {
      this.career = data;
      console.log(this.career);
    });
    this.signupService.getProvince().subscribe(data => {
      this.province = data;
      console.log(this.province);
    });

  }
  SignUp() {
    if (
      this.customer.customerIDs === '' ||
     this.customer.customerPassword === '' ||
     this.customer.customerName === '' ||
     this.customer.customerPhone === '' ||
     this.customer.customerGender === '' ||
     this.customer.customerBirthday === '' ||
     this.customer.customerAddress === '' ||
     this.customer.career === '' ||
     this.customer.province === '' ) {
     alert('ข้อมูลไม่ครบถ้วน');
} else {
  this.signupService.CheckCustomer(this.customer.customerIDs).subscribe(data => {
    console.log( data );
        if ( data != null ) {
         localStorage.setItem('customerIDs', JSON.stringify(data));
         const dialogRef = this.dialog.open(CheckCustomerNotUse, {
          width: '500px'
        });
        dialogRef.afterClosed().subscribe(result => {
          console.log('Can Not Use');
        });
        } else {
          this.httpClient.post('http://localhost:8080/customerRegister/' + this.customer.customerIDs + '/'
  + this.customer.customerPassword + '/' + this.customer.customerName +
      '/' + this.customer.customerPhone + '/' + this.customer.customerGender +
      '/' + this.pipe.transform(this.customer.customerBirthday, 'dd:MM:yyyy') + '/' +
      this.customer.customerAddress + '/' +
      this.customer.career + '/' +
      this.customer.province, this.customerRegister)
  .subscribe(
    datas => {
      window.location.reload();
        window.location.href = '/home';
        console.log('Post successful', datas);
        alert('สำเร็จ');

    },
    error => {
        console.log('Error', error);
    }
);
        }
 });

}
        console.log( this.customer.customerIDs,
        this.customer.customerPassword,
        this.customer.customerName,
        this.customer.customerPhone,
        this.customer.customerGender,
        this.customer.customerBirthday,
        this.customer.customerAddress,
        this.customer.career,
        this.customer.province);
  }
  checkId(id) {
    this.signupService.CheckCustomer(this.customer.customerIDs).subscribe(data => {
      console.log( data );
          if ( data != null ) {
           localStorage.setItem('customerIDs', JSON.stringify(data));
           const dialogRef = this.dialog.open(CheckCustomerNotUse, {
            width: '500px'
          });
          dialogRef.afterClosed().subscribe(result => {
            console.log('Can Not Use');
          });
          } else {
             const dialogRef = this.dialog.open(CheckCustomerCanUse, {
                            width: '500px'
                          });
                          dialogRef.afterClosed().subscribe(result => {
                            console.log('Can Use');
                          });
          }
   });
    console.log(id);
  }

}

@Component ({
  selector: 'app-checkCustomerCanUse',
  templateUrl: './checkCustomerCanUse.html',
})
// tslint:disable-next-line:component-class-suffix
export class CheckCustomerCanUse {
  constructor(
    public dialogRef: MatDialogRef<CheckCustomerCanUse>
    ) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}

@Component ({
  selector: 'app-checkCustomerNotUse',
  templateUrl: './checkCustomerNotUse.html',
})
// tslint:disable-next-line:component-class-suffix
export class CheckCustomerNotUse {
  constructor(
    public dialogRef: MatDialogRef<CheckCustomerNotUse>
    ) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}
