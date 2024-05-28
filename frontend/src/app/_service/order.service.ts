import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Order } from '../_models/order.model';
import { UserCart } from '../_models/user-cart.model';

const AUTH_API_URL = 'https://localhost:8081/user/order/'

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) { }

  getAllOrdersForUser(email: string): Observable<Order[]> {
    return this.http.get<Order[]>(
      AUTH_API_URL + email,
      httpOptions
    )
  }

  getOrderForUser(id: string, email: string): Observable<Order> {
    return this.http.get<Order>(
      AUTH_API_URL + id + '?user=' + email,
      httpOptions
    )
  }

  addOrder(userCart: UserCart[]): Observable<Order> {
    return this.http.post<Order>(
      AUTH_API_URL + 'add',
      userCart,
      httpOptions
    )
  }


  updateOrder(order: Order): Observable<Order> {
    return this.http.put<Order>(
      AUTH_API_URL + 'update',
      order,
      httpOptions
    )
  }
}
