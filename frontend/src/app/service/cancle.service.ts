import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable()
export class CancleService {
  constructor(private http: HttpClient) {
  }
  getBookingCancle(): Observable<any> {
    return this.http.get('//localhost:8080/bookingCancle');
  }
  getBooking(): Observable<any> {
    return this.http.get('//localhost:8080/cbooking');
  }
  getTypeReason(): Observable<any> {
    return this.http.get('//localhost:8080/typeReason');
  }
  CheckbookingCancleReason(CancleReason: String ): Observable<any> {
    return this.http.post('//localhost:8080/checkbookingCancleReason/' + CancleReason , {});
  }
}
