import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {ProductinfoModel} from '../models/productinfo.model';
import {apiUrl} from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  private productUrl = `${apiUrl}/product`;
  private categoryUrl = `${apiUrl}/category`;

  constructor(private http: HttpClient) {
  }

  getAllInPage(page: number, size: number): Observable<any> {
    const url = `${this.productUrl}?page=${page}&size=${size}`;
    return this.http.get(url).pipe();
  }

  getCategoryInPage(categoryType: number, page: number, size: number): Observable<any> {
    const url = `${this.categoryUrl}/${categoryType}?page=${page}&size=${size}`;
    return this.http.get(url).pipe();
  }

  getDetail(id: String): Observable<ProductinfoModel> {
    const url = `${this.productUrl}/${id}`;
    return this.http.get<ProductinfoModel>(url).pipe(
      catchError(_ => {
        return of(new ProductinfoModel());
      })
    );
  }

  update(productInfo: ProductinfoModel): Observable<ProductinfoModel> {
    const url = `${apiUrl}/seller/product/${productInfo.productId}/edit`;
    return this.http.put<ProductinfoModel>(url, productInfo);
  }

  create(productInfo: ProductinfoModel): Observable<ProductinfoModel> {
    const url = `${apiUrl}/seller/product/new`;
    return this.http.post<ProductinfoModel>(url, productInfo);
  }

  delete(productInfo: ProductinfoModel): Observable<any> {
    const url = `${apiUrl}/seller/product/${productInfo.productId}/delete`;
    return this.http.delete(url);
  }
}
