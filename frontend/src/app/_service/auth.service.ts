import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AuthRequest } from '../_models/auth-request.model';
import { AuthResponse } from '../_models/auth-response.model';
import { RegisterRequest } from '../_models/register-request.model';

const AUTH_API_URL = 'http://localhost:8081/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json'})
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(request: AuthRequest): Observable<any>{
    return this.http.post(
      AUTH_API_URL + 'singin', 
      request,
      httpOptions
    );
  };

  register(request: RegisterRequest): Observable<any>{
    return this.http.post(
      AUTH_API_URL + 'register',
      request,
      httpOptions
    );
  };
}
