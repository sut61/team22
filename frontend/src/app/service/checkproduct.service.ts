import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
@Injectable({
  providedIn: 'root'
})

export class CheckproductService {
  public API = '//localhost:8080';

  constructor(private http: HttpClient) { }

  getProduct(): Observable<any> {
    return this.http.get(this.API + '/product');
  }
  getType(): Observable<any> {
    return this.http.get(this.API + '/type');
  }
  getStatus(): Observable<any> {
    return this.http.get(this.API + '/status');
  }
  getCheckProduct(): Observable<any> {
    return this.http.get(this.API + '/checkproduct');
  }
  getChecking(): Observable<any> {
    return this.http.get(this.API + '/checking');
  }
}