import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})

export class RestoreService {

  constructor(private http: HttpClient) {

  }
  getRestore(): Observable<any> {
    return this.http.get('//localhost:8080/restore');
  }

  getLease(): Observable<any> {
    return this.http.get('//localhost:8080/rlease');
  }
  getRestoreType():Observable<any>{
    return this.http.get('//localhost:8080/restoretype');
  }
  CheckCommentRestore(CheckCommentRestore: String ): Observable<any> {
    return this.http.post('//localhost:8080/CheckCommentRestore/' + CheckCommentRestore , {} );
    }
}

