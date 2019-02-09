import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class ReviewService {
  constructor(private http: HttpClient) {
  }
  getCustomer(): Observable<any> {
    return this.http.get('//localhost:8080/customer');
  }
  getSelling(): Observable<any> {
    return this.http.get('//localhost:8080/SellingReview');
  }
  getReview(): Observable<any> {
    return this.http.get('//localhost:8080/review');
  }
  getLevelReview(): Observable<any> {
    return this.http.get('//localhost:8080/levelReview');
  }
  CheckReviewComment(reviewComment: String ): Observable<any> {
    return this.http.post('//localhost:8080/CheckReviewComment/' + reviewComment , {} );
  }
}
