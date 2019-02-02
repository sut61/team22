import {Component, OnInit} from '@angular/core';
import {FormControl} from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import {DatePipe} from '@angular/common';
import { SignupService } from '../service/signup.service';

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

  constructor(private signupService: SignupService, private httpClient: HttpClient) { }

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
  this.httpClient.post('http://localhost:8080/customerRegister/' + this.customer.customerIDs + '/'
  + this.customer.customerPassword + '/' + this.customer.customerName +
      '/' + this.customer.customerPhone + '/' + this.customer.customerGender +
      '/' + this.pipe.transform(this.customer.customerBirthday, 'dd:MM:yyyy') + '/' +
      this.customer.customerAddress + '/' +
      this.customer.career + '/' +
      this.customer.province, this.customerRegister)
  .subscribe(
    data => {
      window.location.reload();
        window.location.href = '/home';
        console.log('Post successful', data);
        alert('สำเร็จ');

    },
    error => {
        console.log('Error', error);
    }
);
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

    console.log(id);
  }
}
