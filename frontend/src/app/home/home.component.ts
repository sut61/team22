import { SigninService } from '../service/signin.service';
import { SignupService } from '../service/signup.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {MatDialog , MatDialogRef} from '@angular/material';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})

export class HomeComponent implements OnInit {
  hide = true;

  login = {
    userId: '',
    upassword: '',
    admin: '',
    apassword: ''
  };

  constructor( private signinService: SigninService, private signupService: SignupService
    , private router: Router , public dialog: MatDialog) { }

  ngOnInit() {
  }

  SignInUser() {
    this.signinService.findCustomer(this.login.userId , this.login.upassword).subscribe(data => {
       console.log( data );
           if ( data != null ) {
            localStorage.setItem('id', JSON.stringify(data));
                this.router.navigate(['./makeup' , { first: data}]);
           } else {
              const dialogRef = this.dialog.open(Loginfalse, {
                             width: '500px'
                           });

                           dialogRef.afterClosed().subscribe(result => {
                             console.log('The dialog was closed');

                           });
            localStorage.setItem('id' , JSON.stringify(data));
            this.login.upassword = null ;
           }
    });
}

SignInAdmin() {
  this.signinService.findAdmin(this.login.admin , this.login.apassword).subscribe(data => {
     console.log( data );
         if ( data != null ) {
          localStorage.setItem('admin', JSON.stringify(data));
              this.router.navigate(['./payment' , { first: data.name}]);
         } else {
            const dialogRef = this.dialog.open(Loginfalse, {
                           width: '500px'
                         });

                         dialogRef.afterClosed().subscribe(result => {
                           console.log('The dialog was closed');

                         });
          localStorage.setItem('admin' , JSON.stringify(data));
          this.login.apassword = null ;
         }
  });
}

}

  @Component ({
    selector: 'app-loginfalse',
    templateUrl: './loginfalse.html',
  })
  // tslint:disable-next-line:component-class-suffix
  export class Loginfalse {
    constructor(
      public dialogRef: MatDialogRef<Loginfalse>
      ) {}
    onNoClick(): void {
      this.dialogRef.close();
    }
  }

