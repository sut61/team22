import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
@Injectable({
  providedIn: 'root'
})
export class SalaryService {

  constructor(private http: HttpClient) {
  }
  getStaff(): Observable<any> {
    return this.http.get('//localhost:8080/staff');
  }
  getPayer(): Observable<any> {
    return this.http.get('//localhost:8080/payer');
  }
  getSalary(): Observable<any> {
    return this.http.get('//localhost:8080/salary');
  }
  getSalary1(): Observable<any> {
    return this.http.get('//localhost:8080/salary1');
  }
  getSalary2(): Observable<any> {
    return this.http.get('//localhost:8080/salary2');
  }
  // getStaffCheck(id): Observable<any> {
  //   return this.http.get('//localhost:8080/salary1/' + id);
  // }
}
