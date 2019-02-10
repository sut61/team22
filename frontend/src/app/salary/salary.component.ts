import { Router } from '@angular/router';
import { DataSource } from '@angular/cdk/collections';
import { SalaryService } from './../service/salary.service';
import { Component, OnInit } from '@angular/core';
import { ViewChild } from '@angular/core';
import {
  MatSort,
  MatSnackBar,
  MatDialog,
  MatDialogRef
} from '@angular/material';
import { HttpClient } from '@angular/common/http';
import { MatPaginator, MatTableDataSource } from '@angular/material';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-salary',
  templateUrl: './salary.component.html',
  styleUrls: ['./salary.component.css']
})
export class SalaryComponent implements OnInit {
  displayedColumns: string[] = [
    'salaryIds',
    'staffIds',
    'staffName',
    'staffGender',
    'staffStatus',
    'staffSalary',
    'positionId',
    'salaryDate'
  ];
  displayedColumns1: string[] = [
    'salaryIds',
    'staffIds',
    'staffName',
    'staffGender',
    'staffStatus',
    'staffSalary',
    'positionId',
    'salaryDate'
  ];
  displayedColumns2: string[] = [
    'salaryIds',
    'staffIds',
    'staffName',
    'staffGender',
    'staffStatus',
    'staffSalary',
    'positionId',
    'salaryDate'
  ];
  displayedColumns3: string[] = [
    'staffIds',
    'staffName',
    'staffGender',
    'educationId',
    'staffPhone',
    'staffJobtype',
    'staffSalary',
    'positionId',
    'experienceId'
  ];

  Staffs: Array<any>;
  staffId: Array<any>;
  staffName: Array<any>;
  staffIds: Array<any>;
  staffSalary: Array<any>;
  staffStatus: Array<any>;
  StaffCheck: Array<any>;

  Salary: Array<any>;
  Salarys: Array<any>;
  Salaryss: Array<any>;
  Salarysss: Array<any>;
  salaryId: Array<any>;
  salaryIds: Array<any>;

  Payers: Array<any>;


  views: any = {
    salaryId: '',
    staffId: '',
    staffName: '',
    staffStatus: '',
    staffSalary: '',
    payerId: '',
    SelectSalaryId: '',
    SelectStaffId: '',
    SelectStaffName: '',
    SelectStaffStatus: '',
    SelectStaffSalary: '',
    SelectPayerId: ''
  };

  @ViewChild(MatSort)
  sort: MatSort;

  constructor(
    private salaryservice: SalaryService,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router,
    public dialog: MatDialog
  ) { }

  ngOnInit() {
    this.salaryservice.getSalary().subscribe(data => {
      this.Salarys = data;
      console.log(this.Salarys);
    });
    this.salaryservice.getSalary1().subscribe(data => {
      this.Salaryss = data;
      console.log(this.Salaryss);
    });
    this.salaryservice.getSalary2().subscribe(data => {
      this.Salarysss = data;
      console.log(this.Salarysss);
    });
    this.salaryservice.getStaff().subscribe(data => {
      this.Staffs = data;
      console.log(this.Staffs);
    });
    this.salaryservice.getPayer().subscribe(data => {
      this.Payers = data;
      console.log(this.Payers);
    });
  }

  selectRow(row) {
    this.views.SelectSalaryId = row.salaryId;
    this.views.SelectPayerId = row.payer.payerId;
    this.views.SelectStaffId = row.staff.staffId;
    this.views.SelectStaffName = row.staff.staffName;
    this.views.SelectStaffSalary = row.staff.staffSalary;
    this.views.SelectStaffStatus = row.staff.staffStatus;
  }

  selectRow2(row) {
    this.views.SelectStaffId = row.staffId;
    this.views.SelectStaffIds = row.staffIds;
    this.views.SelectStaffName = row.staffName;
    this.views.SelectStaffPhone = row.staffPhone;
    this.views.SelectStaffSalary = row.staffSalary;
    this.views.SelectStaffGender = row.staffGender;
    this.views.SelectStaffJobtype = row.staffJobtype;
    this.views.SelectPositionId = row.position.positionId;
    this.views.SelectEducationId = row.education.educationId;
    this.views.SelectExperienceId = row.experience.experienceId;
  }

  Update() {
    this.views.salaryId = this.views.SelectSalaryId;
    this.views.staffId = this.views.SelectStaffId;
    this.views.staffSalary = this.views.SelectStaffSalary;

    if (!this.views.staffStatus) {
      this.snackBar.open('กรุณาเลือก Status');
      this.views.staffStatus = this.views.SelectStaffStatus;
    }else{

    this.httpClient
      .put(
        'http://localhost:8080/salary/' +
        this.views.salaryId +
        '/' +
        this.views.staffId +
        '/' +
        this.views.staffStatus +
        '/' +
        new Date() +
        '/' +
        this.views.staffSalary,
        this.Salarys
      )
      .subscribe(dataRegister => {
        const dialogRef = this.dialog.open(SalaryEditcomplete, {
          width: '500px'
        });
        dialogRef.afterClosed().subscribe(
          result => {
            window.location.href = '/salary';
            // console.log('Can SignIp');
            // this.snackBar.open('ลงทะเบียนสำเร็จ');
          },
          error => {
            const dialogRe = this.dialog.open(SalaryEditcomplete, {
              width: '500px'
            });
            dialogRe.afterClosed().subscribe(result => {
              // console.log('Can Not SignIp');
            });
            // console.log('Error', error);
          }
        );
      });
    }
  }
}

@Component({
  selector: 'app-salaryeditcomplete',
  templateUrl: './salaryeditcomplete.html'
})

// tslint:disable-next-line:component-class-suffix
export class SalaryEditcomplete {
  constructor(public dialogRef: MatDialogRef<SalaryEditcomplete>) { }
  onNoClick(): void {
    this.dialogRef.close();
  }
}
