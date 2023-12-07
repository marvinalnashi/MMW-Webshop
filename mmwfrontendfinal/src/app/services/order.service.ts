import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {catchError} from 'rxjs/operators';
import {Observable, of} from 'rxjs';
import {OrderModel} from '../models/order.model';
import {apiUrl} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private orderUrl = `${apiUrl}/order`;

  constructor(private http: HttpClient) {
  }

  getPage(page = 1, size = 10): Observable<any> {
    return this.http.get(`${this.orderUrl}?page=${page}&size=${size}`).pipe();
  }

  show(id): Observable<OrderModel> {
    return this.http.get<OrderModel>(`${this.orderUrl}/${id}`).pipe(
      catchError(_ => of(null))
    );
  }

  cancel(id): Observable<OrderModel> {
    return this.http.patch<OrderModel>(`${this.orderUrl}/cancel/${id}`, null).pipe(
      catchError(_ => of(null))
    );
  }

  finish(id): Observable<OrderModel> {
    return this.http.patch<OrderModel>(`${this.orderUrl}/finish/${id}`, null).pipe(
      catchError(_ => of(null))
    );
  }
}
