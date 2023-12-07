import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {apiUrl} from '../../environments/environment';
import {BehaviorSubject, Observable, of, Subject} from 'rxjs';
import {tap} from 'rxjs/operators';
import {JwtResponse} from '../response/JwtResponse';
import {CookieService} from 'ngx-cookie-service';
import {UserModel} from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private currentUserSubject: BehaviorSubject<JwtResponse>;
  public currentUser: Observable<JwtResponse>;
  public nameTerms = new Subject<string>();
  public name$ = this.nameTerms.asObservable();
  constructor(private http: HttpClient, private cookieService: CookieService) {
    const savedUser = localStorage.getItem('currentUser');
    this.currentUserSubject = new BehaviorSubject<JwtResponse>(JSON.parse(savedUser));
    this.currentUser = this.currentUserSubject.asObservable();
    cookieService.set('currentUser', savedUser);
  }

  get currentUserValue() {
    return this.currentUserSubject.value;
  }

  login(loginForm): Observable<JwtResponse> {
    const url = `${apiUrl}/login`;
    return this.http.post<JwtResponse>(url, loginForm).pipe(
      tap(user => {
        if (user && user.token) {
          this.cookieService.set('currentUser', JSON.stringify(user));
          this.nameTerms.next(user.name);
          this.currentUserSubject.next(user);
          return user;
        }
      })
    );
  }

  logout() {
    this.currentUserSubject.next(null);
    localStorage.removeItem('currentUser');
    this.cookieService.delete('currentUser');
  }

  signUp(user: UserModel): Observable<UserModel> {
    const url = `${apiUrl}/register`;
    return this.http.post<UserModel>(url, user);
  }

  update(user: UserModel): Observable<UserModel> {
    const url = `${apiUrl}/profile`;
    return this.http.put<UserModel>(url, user);    }

  get(email: string): Observable<UserModel> {
    const url = `${apiUrl}/profile/${email}`;
    return this.http.get<UserModel>(url);
  }
}
