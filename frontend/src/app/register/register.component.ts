import { Component, OnInit, ViewChild } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Router } from "@angular/router";
import { MatDialog, MatDialogRef, MatSort } from "@angular/material";
import { MatSnackBar } from "@angular/material";
import { RegisterService } from "../service/register.service";

@Component({
  selector: "app-register",
  templateUrl: "./register.component.html",
  styleUrls: ["./register.component.css"]
})
export class RegisterComponent implements OnInit {
  displayedColumns: string[] = [
    "staffIds",
    "staffName",
    "staffGender",
    "educationId",
    "staffPhone",
    "staffJobtype",
    "staffSalary",
    "positionId",
    "experienceId"
  ];
  Staff: Array<any>;
  Staffs: Array<any>;
  staffId: Array<any>;
  staffIds: Array<any>;
  staffName: Array<any>;
  staffPhone: Array<any>;
  staffStatus: Array<any>;
  staffGender: Array<any>;
  staffSalary: Array<any>;
  staffJobtype: Array<any>;

  Experience: Array<any>;
  Experiences: Array<any>;
  experienceId: Array<any>;
  experienceName: Array<any>;

  Position: Array<any>;
  Positions: Array<any>;
  positionId: Array<any>;
  positionName: Array<any>;

  Educations: Array<any>;
  educationName: Array<any>;

  Salarys: Array<any>;

  views: any = {
    staffId: "",
    staffIds: "",
    staffName: "",
    staffPhone: "",
    staffGender: "",
    staffStatus: "",
    staffSalary: "",
    staffJobtype: "",
    staffPassword: "",

    positionId: "",

    experienceId: "",

    educationId: "",

    payerId: "",

    SelectStaffId: "",
    SelectStaffIds: "",
    SelectStaffName: "",
    SelectStaffPhone: "",
    SelectStaffGender: "",
    SelectStaffSalary: "",
    SelectStaffJobtype: "",
    SelectStaffPassword: "",

    SelectPositionId: "",

    SelectExperienceId: "",

    SelectEducationId: ""
  };

  @ViewChild(MatSort)
  sort: MatSort;

  constructor(
    private registerservice: RegisterService,
    private httpClient: HttpClient,
    private snackBar: MatSnackBar,
    private router: Router,
    public dialog: MatDialog
  ) {}

  ngOnInit() {
    this.registerservice.getPosition().subscribe(data => {
      this.Positions = data;
      console.log(this.Positions);
    });
    this.registerservice.getExperience().subscribe(data => {
      this.Experiences = data;
      console.log(this.Experiences);
    });
    this.registerservice.getStaff().subscribe(data => {
      this.Staffs = data;
      console.log(this.Staffs);
    });
    this.registerservice.getEducation().subscribe(data => {
      this.Educations = data;
      console.log(this.Educations);
    });
    this.registerservice.getSalary().subscribe(data => {
      this.Salarys = data;
      console.log(this.Salarys);
    });
  }

  selectRow(row) {
    this.views.SelectStaffId = row.staffId;
    this.views.SelectStaffIds = row.staffIds;
    this.views.SelectStaffName = row.staffName;
    this.views.SelectStaffPhone = row.staffPhone;
    this.views.SelectStaffGender = row.staffGender;
    this.views.SelectStaffSalary = row.staffSalary;
    this.views.SelectStaffJobtype = row.staffJobtype;
    this.views.SelectStaffPassword = row.staffPassword;

    this.views.SelectEducationId = row.education.educationId;

    this.views.SelectPositionId = row.position.positionId;

    this.views.SelectExperienceId = row.experience.experienceId;
  }

  Post1() {
    const rex1 = new RegExp("(\\S{12})");
    const rex = new RegExp("([0][0-9]{2}-[0-9]{3}-[0-9]{4})");
    this.views.staffPhone.charAt(0);
    console.log(this.views.staffPhone.charAt(0));
    if (
      this.views.staffName === "" ||
      this.views.staffPhone === "" ||
      this.views.positionId === "" ||
      this.views.staffGender === "" ||
      this.views.educationId === "" ||
      this.views.staffSalary === "" ||
      this.views.experienceId === "" ||
      this.views.staffJobtype === "" ||
      this.views.staffPassword === ""
    ) {
      this.snackBar.open("กรุณาใส่ข้อมูลให้ครบ");
    } else {
      this.registerservice
        .postStaffCheck(this.views.staffName)
        .subscribe(postStaffCheck => {
          console.log(postStaffCheck);
          if (postStaffCheck != null) {
            this.snackBar.open("ไม่สามารถใช้ Name นี้ได้", "ตกลง", {});
          } else {
            if (!rex1.test(this.views.staffPhone)) {
              this.snackBar.open("Size Phone ไม่ถูกต้อง");
              console.log(this.views.staffPhone);
            } else {
              if (rex.test(this.views.staffPhone)) {
                this.httpClient
                  .post(
                    "http://localhost:8080/staffs/" +
                      this.views.staffName +
                      "/" +
                      this.views.staffGender +
                      "/" +
                      this.views.educationId +
                      "/" +
                      this.views.staffPhone +
                      "/" +
                      this.views.staffJobtype +
                      "/" +
                      this.views.staffSalary +
                      "/" +
                      this.views.positionId +
                      "/" +
                      "UnPaid" +
                      "/" +
                      this.views.experienceId +
                      "/" +
                      this.views.staffPassword,
                    this.Staffs
                  )
                  .subscribe(dataRegister => {
                    console.log("Post successful", dataRegister);
                    localStorage.setItem(
                      "staffName",
                      JSON.stringify(postStaffCheck)
                    );
                    const dialogRef = this.dialog.open(RegisterComplete, {
                      width: "500px"
                    });
                    dialogRef.afterClosed().subscribe(
                      result => {
                        window.location.href = "/register";
                        console.log("Can Post Staff");
                        this.snackBar.open("ลงทะเบียนสำเร็จ");
                      },
                      error => {
                        const dialogRe = this.dialog.open(RegisterUncomplete, {
                          width: "500px"
                        });
                        dialogRe.afterClosed().subscribe(result => {
                          console.log("Can Not Post Staff");
                        });
                        console.log("Error", error);
                      }
                    );
                  });
              } else {
                this.snackBar.open("Pattern Phone ไม่ถูกต้อง");
              }
            }
          }
        });
    }
  }

  Post2() {
    if (
      this.views.SelectStaffName === "" ||
      this.views.SelectStaffSalary === ""
    ) {
      this.snackBar.open("กรุณาใส่ข้อมูลให้ครบ2");
    }
    this.httpClient
      .post(
        "http://localhost:8080/salaryPost/" +
          this.views.SelectStaffName +
          "/" +
          "1" +
          "/" +
          "UnPaid" +
          "/" +
          this.views.SelectStaffSalary,
        this.Salarys
      )
      .subscribe(dataRegister => {
        const dialogRef = this.dialog.open(RegisterComplete, {
          width: "500px"
        });
        dialogRef.afterClosed().subscribe(
          result => {
            window.location.href = "/register";
            console.log("Can Post Salary");
          },
          error => {
            const dialogRe = this.dialog.open(RegisterUncomplete, {
              width: "500px"
            });
            dialogRe.afterClosed().subscribe(result => {});
            console.log("Can Not Post Salary");
          }
        );
      });
  }

  Update() {
    this.views.staffId = this.views.SelectStaffId;
    this.views.staffIds = this.views.SelectStaffIds;

    if (!this.views.staffName) {
      this.views.staffName = this.views.SelectStaffName;
    }

    if (!this.views.staffPhone) {
      this.views.staffPhone = this.views.SelectStaffPhone;
    }

    if (!this.views.staffSalary) {
      this.views.staffSalary = this.views.SelectStaffSalary;
    }

    if (!this.views.staffGender) {
      this.views.staffGender = this.views.SelectStaffGender;
    }

    if (!this.views.educationId) {
      this.views.educationId = this.views.SelectEducationId;
    }

    if (!this.views.positionId) {
      this.views.positionId = this.views.SelectPositionId;
    }

    if (!this.views.staffJobtype) {
      this.views.staffJobtype = this.views.SelectStaffJobtype;
    }

    if (!this.views.experienceId) {
      this.views.experienceId = this.views.SelectExperienceId;
    }

    if (!this.views.staffPassword) {
      this.views.staffPassword = this.views.SelectStaffPassword;
    }
    const rex1 = new RegExp("(\\S{12})");
    const rex = new RegExp("([0][0-9]{2}-[0-9]{3}-[0-9]{4})");
    this.views.staffPhone.charAt(0);
    console.log(this.views.staffPhone.charAt(0));
            if (!rex1.test(this.views.staffPhone)) {
              this.snackBar.open("Size Phone ไม่ถูกต้อง");
              console.log(this.views.staffPhone);
            } else {
              if (rex.test(this.views.staffPhone)) {
                this.httpClient
                  .put(
                    "http://localhost:8080/staffupdate/" +
                      this.views.SelectStaffId +
                      "/" +
                      this.views.SelectStaffIds +
                      "/" +
                      this.views.staffName +
                      "/" +
                      this.views.staffPhone +
                      "/" +
                      this.views.staffSalary +
                      "/" +
                      "UnPaid" +
                      "/" +
                      this.views.positionId +
                      "/" +
                      this.views.educationId +
                      "/" +
                      this.views.staffGender +
                      "/" +
                      this.views.staffJobtype +
                      "/" +
                      this.views.experienceId +
                      "/" +
                      this.views.staffPassword,
                    this.Staffs
                  )
                  .subscribe(dataRegister => {
                    console.log("Put successful", dataRegister);
                   });
                    const dialogRef = this.dialog.open(RegisterComplete, {
                      width: "500px"
                    });
                    dialogRef.afterClosed().subscribe(
                      result => {
                        window.location.href = "/register";
                        console.log("Can Post Staff");
                        this.snackBar.open("แก้ไขสำเร็จ");
                      },
                      error => {
                        const dialogRe = this.dialog.open(RegisterUncomplete, {
                          width: "500px"
                        });
                        dialogRe.afterClosed().subscribe(result => {
                          console.log("Can Not Put Staff");
                        });
                        console.log("Error", error);
                      });

              } else {
                this.snackBar.open("Pattern Phone ไม่ถูกต้อง");
              }
            }
          }
        }

@Component({
  selector: "app-registercomplete",
  templateUrl: "./registercomplete.html"
})
export class RegisterComplete {
  constructor(public dialogRef: MatDialogRef<RegisterComplete>) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}

@Component({
  selector: "app-registeruncomplete",
  templateUrl: "./registeruncomplete.html"
})
export class RegisterUncomplete {
  constructor(public dialogRef: MatDialogRef<RegisterUncomplete>) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}

@Component({
  selector: "app-registereditcomplete",
  templateUrl: "./registereditcomplete.html"
})
export class RegisterEditcomplete {
  constructor(public dialogRef: MatDialogRef<RegisterEditcomplete>) {}
  onNoClick(): void {
    this.dialogRef.close();
  }
}
