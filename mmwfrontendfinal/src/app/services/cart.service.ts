import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../environments/environment';
import {CookieService} from 'ngx-cookie-service';
import {BehaviorSubject, Observable, of} from 'rxjs';
import {catchError, map, tap} from 'rxjs/operators';
import {UserService} from './user.service';
import {CartModel} from '../models/cart.model';
import {ItemModel} from '../models/item.model';
import {JwtResponse} from '../response/JwtResponse';
import {ProductinorderModel} from '../models/productinorder.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {


  private cartUrl = `${apiUrl}/cart`;

  localMap = {};


  private itemsSubject: BehaviorSubject<ItemModel[]>;
  private totalSubject: BehaviorSubject<number>;
  public items: Observable<ItemModel[]>;
  public total: Observable<number>;
  private currentUser: JwtResponse;

  constructor(private http: HttpClient, private cookieService: CookieService, private userService: UserService) {
    this.itemsSubject = new BehaviorSubject<ItemModel[]>(null);
    this.items = this.itemsSubject.asObservable();
    this.totalSubject = new BehaviorSubject<number>(null);
    this.total = this.totalSubject.asObservable();
    this.userService.currentUser.subscribe(user => this.currentUser = user);
  }

  private getLocalCart(): ProductinorderModel[] {
    if (this.cookieService.check('cart')) {
      this.localMap = JSON.parse(this.cookieService.get('cart'));
      return Object.values(this.localMap);
    } else {
      this.localMap = {};
      return [];
    }
  }

  getCart(): Observable<ProductinorderModel[]> {
    const localCart = this.getLocalCart();
    if (this.currentUser) {
      if (localCart.length > 0) {
        return this.http.post<CartModel>(this.cartUrl, localCart).pipe(
          tap(_ => {
            this.clearLocalCart();
          }),
          map(cart => cart.products),
          catchError(_ => of([]))
        );
      } else {
        return this.http.get<CartModel>(this.cartUrl).pipe(
          map(cart => cart.products),
          catchError(_ => of([]))
        );
      }
    } else {
      return of(localCart);
    }
  }

  addItem(productInOrder): Observable<boolean> {
    if (!this.currentUser) {
      if (this.cookieService.check('cart')) {
        this.localMap = JSON.parse(this.cookieService.get('cart'));
      }
      if (!this.localMap[productInOrder.productId]) {
        this.localMap[productInOrder.productId] = productInOrder;
      } else {
        this.localMap[productInOrder.productId].count += productInOrder.count;
      }
      this.cookieService.set('cart', JSON.stringify(this.localMap));
      return of(true);
    } else {
      const url = `${this.cartUrl}/add`;
      return this.http.post<boolean>(url, {
        'quantity': productInOrder.count,
        'productId': productInOrder.productId
      });
    }
  }

  update(productInOrder): Observable<ProductinorderModel> {
    if (this.currentUser) {
      const url = `${this.cartUrl}/${productInOrder.productId}`;
      return this.http.put<ProductinorderModel>(url, productInOrder.count);
    }
  }

  remove(productInOrder) {
    if (!this.currentUser) {
      delete this.localMap[productInOrder.productId];
      return of(null);
    } else {
      const url = `${this.cartUrl}/${productInOrder.productId}`;
      return this.http.delete(url).pipe( );
    }
  }

  checkout(): Observable<any> {
    const url = `${this.cartUrl}/checkout`;
    return this.http.post(url, null).pipe();
  }

  storeLocalCart() {
    this.cookieService.set('cart', JSON.stringify(this.localMap));
  }

  clearLocalCart() {
    this.cookieService.delete('cart');
    this.localMap = {};
  }
}
