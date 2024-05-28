import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Product } from '../_models/product.model';
import { Observable } from 'rxjs';
import { PagedResponse } from '../_models/paged-response.model';

const AUTH_API_URL = 'https://localhost:8081/user/product';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }
  
  refreshProducts(page: number, size:number): Observable<PagedResponse> {
    const sufix: string = (page !== undefined && size != undefined)
      ? "?page=" + page + "&size=" + size
      : ""

    return this.http.get<PagedResponse>(
      AUTH_API_URL + sufix,
      httpOptions,
    );
  };

  findeProductById(id: string): Observable<Product> {
    return this.http.get<Product>(
      AUTH_API_URL + id,
      httpOptions
    );
  };
}
