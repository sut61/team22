import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class SigninService {

  constructor(private http: HttpClient) {  }

 getCustomerLogin(): Observable<any> {
  return this.http.get('//localhost:8080/customer');
  }
 findCustomer(user: String , pass: String): Observable<any> {
  return this.http.post('//localhost:8080/customerLogin/' + user + '/password/' + pass, {} );
  }
  findAdmin(admin: String , pass: String): Observable<any> {
    return this.http.post('//localhost:8080/adminLogin/' + admin + '/password/' + pass, {} );
    }
}
