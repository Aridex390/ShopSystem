import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserCart } from '../_models/user-cart.model';
import { Observable } from 'rxjs';

const AUTH_API_URL = 'https://localhost:8081/user/cart';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class UserCartService {

  constructor(private http: HttpClient) { }

  getUserCart(email: string): Observable<UserCart[]> {
    return this.http.get<UserCart[]>(
      AUTH_API_URL + email,
      httpOptions
    );
  };

  increaseCartItem(id: string): Observable<UserCart> {
    return this.http.put<UserCart>(
      AUTH_API_URL + 'increase/' + id,
      httpOptions
    );
  };

  decreaseCartItem(id: string): Observable<UserCart> {
    return this.http.put<UserCart>(
      AUTH_API_URL + 'decrease/' + id,
      httpOptions
    );
  };

  addCartItemforUser(userCart: UserCart, email: string): Observable<UserCart[]> {
    return this.http.post<UserCart[]>(
      AUTH_API_URL + 'add/user=' + email,
      userCart,
      httpOptions
    );
  };

  deleteCartItem(id: string): Observable<UserCart[]> {
    return this.http.delete<UserCart[]>(
      AUTH_API_URL + id,
      httpOptions
    );
  };
}
