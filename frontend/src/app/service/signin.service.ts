import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class SigninService {

  constructor(private http: HttpClient) {  }

 findCustomer(userId: String , pass: String): Observable<any> {
  return this.http.post('//localhost:8080/customerSignin/' + userId + '/' + pass, {} );
  }

 findAdmin(admin: String , pass: String): Observable<any> {
  return this.http.post('//localhost:8080/adminLogin/' + admin + '/password/' + pass, {} );
  }
}
