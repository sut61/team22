import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private http: HttpClient) {}

  getCareer(): Observable<any> {
      return this.http.get('//localhost:8080/career');
  }
  getProvince(): Observable<any> {
    return this.http.get('//localhost:8080/province');
}
    CheckCustomer(customerIDs: String ): Observable<any> {
      return this.http.post('//localhost:8080/customerCheck/' + customerIDs , {} );
      }

}
