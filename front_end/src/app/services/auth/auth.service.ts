// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  private isAuthenticated = new BehaviorSubject<boolean>(false);

  constructor(private http: HttpClient, private router: Router) {
    this.checkAuthStatus();
  }

  get isLoggedIn(): Observable<boolean> {
    return this.isAuthenticated.asObservable();
  }

  login(credentials: { userCode: string, password: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/login`, credentials).pipe(
      tap(() => this.checkAuthStatus())
    );
  }

  logout(): void {
    localStorage.removeItem('jwt_token');
    this.isAuthenticated.next(false);
    this.router.navigate(['/login']);
  }

  changePassword(passwords: { currentPassword: string, newPassword: string }): Observable<any> {
    return this.http.post(`${this.apiUrl}/change_password`, passwords);
  }

  private checkAuthStatus(): void {
    const token = localStorage.getItem('jwt_token');
    this.isAuthenticated.next(!!token);
  }

  getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }
}