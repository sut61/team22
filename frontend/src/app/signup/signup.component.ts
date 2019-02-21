import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { DatePipe } from '@angular/common';
import { SignupService } from '../service/signup.service';
import { Router } from '@angular/router';
import { MatDialog, MatDialogRef } from '@angular/material';
import { MatSnackBar } from '@angular/material';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
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
  num: '10';
  customer = {
    cusId: '',
    customerIDs: '',
    customerPassword: '',
    customerName: '',
    customerPhone: '',
    customerGender: '',
    customerBirthday: '',
    customerAddress: '',
    career: '',
    province: ''
  };

  constructor(
    private signupService: SignupService,
    private snackBar: MatSnackBar,
    private httpClient: HttpClient,
    private router: Router,
    public dialog: MatDialog
  ) {}

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
    const rex = new RegExp('[0]\\d{9}');
    const address = new RegExp('[A-Za-zw0-9d]{2,100}');
    this.customer.customerPhone.charAt(0);
    console.log(this.customer.customerPhone.charAt(0));
    if (
      this.customer.customerIDs === '' ||
      this.customer.customerPassword === '' ||
      this.customer.customerName === '' ||
      this.customer.customerPhone === '' ||
      this.customer.customerGender === '' ||
      this.customer.customerBirthday === '' ||
      this.customer.customerAddress === '' ||
      this.customer.career === '' ||
      this.customer.province === ''
    ) {
      this.snackBar.open('กรุณาใส่ข้อมูลให้ครบ');
    } else {
      this.signupService
        .CheckCustomer(this.customer.customerIDs)
        .subscribe(checkCustomer => {
          console.log(checkCustomer);
          if (checkCustomer != null) {
            this.snackBar.open('ไม่สามารถใช้ ID นี้ได้', 'ตกลง', {});
          } else {
            if (rex.test(this.customer.customerPhone)) {
              if (address.test(this.customer.customerAddress)) {
                this.httpClient
                  .post(
                    'http://localhost:8080/customerSignup/' +
                      this.customer.customerIDs +
                      '/' +
                      this.customer.customerPassword +
                      '/' +
                      this.customer.customerName +
                      '/' +
                      this.customer.customerPhone +
                      '/' +
                      this.customer.customerGender +
                      '/' +
                      this.pipe.transform(
                        this.customer.customerBirthday,
                        'dd:MM:yyyy'
                      ) +
                      '/' +
                      this.customer.customerAddress +
                      '/' +
                      this.customer.career +
                      '/' +
                      this.customer.province,
                    this.customerRegister
                  )
                  .subscribe(dataRegister => {
                    console.log('Post successful', dataRegister);
                    localStorage.setItem(
                      'customerIDs',
                      JSON.stringify(checkCustomer)
                    );
                    const dialogRef = this.dialog.open(Signincomplete, {
                      width: '500px'
                    });
                    dialogRef.afterClosed().subscribe(
                      result => {
                        window.location.href = '/home';
                        console.log('Can SignIp');
                        this.snackBar.open('ลงทะเบียนสำเร็จ');
                      },
                      error => {
                        const dialogRe = this.dialog.open(Signinuncomplete, {
                          width: '500px'
                        });
                        dialogRe.afterClosed().subscribe(result => {
                          console.log('Can Not SignIp');
                        });
                        console.log('Error', error);
                      }
                    );
                  });
              } else {
                this.snackBar.open('ที่อยู่ต้องมีอยู่จริง ความยาวไม่ถูกต้อง');
              }
            } else {
              this.snackBar.open(
                'เบอร์โทรไม่ถูกต้อง ต้องเท่ากับ 10 ตัว และ ตัวเลขเป็นเลข 0 เท่านั้น'
              );
            }
          }
        });
    }
    console.log(
      this.customer.customerIDs,
      this.customer.customerPassword,
      this.customer.customerName,
      this.customer.customerPhone,
      this.customer.customerGender,
      this.customer.customerBirthday,
      this.customer.customerAddress,
      this.customer.career,
      this.customer.province
    );
  }

  checkId(id) {
    this.signupService
      .CheckCustomer(this.customer.customerIDs)
      .subscribe(checkCustomer => {
        console.log(checkCustomer);
        if (checkCustomer != null) {
          this.snackBar.open('ไม่สามารถใช้ ID นี้ได้', 'ตกลง', {});
        } else {
          this.snackBar.open('สามารถใช้ ID นี้ได้', 'ตกลง', {});
        }
      });
    console.log(id);
  }
}

@Component({
  selector: 'app-signincomplete',
  templateUrl: './signincomplete.html'
})
// tslint:disable-next-line:component-class-suffix
export class Signincomplete {
  constructor(public dialogRef: MatDialogRef<Signincomplete>) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}
@Component({
  selector: 'app-signinuncomplete',
  templateUrl: './signinuncomplete.html'
})
// tslint:disable-next-line:component-class-suffix
export class Signinuncomplete {
  constructor(public dialogRef: MatDialogRef<Signinuncomplete>) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}
