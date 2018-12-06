import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { User } from '../model/user';

export const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': ''
  })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  isLoggedIn = false;
  redirectUrl: string;
  user: User;
  token: string;

  private authUrl = 'http://localhost:8080/users';

  constructor(
    private http: HttpClient
  ) { }

  async login(username: string, password: string): Promise<User> {
    try {
      this.token = btoa(`${username}:${password}`);
      httpOptions.headers = httpOptions.headers.set('Authorization', `Basic ${this.token}`);
      this.user = await this.http.post<User>(`${this.authUrl}/login`, httpOptions).toPromise();
      this.isLoggedIn = true;
      return this.user;
    } catch (e) {
      console.log(e);
      throw e;
    }
  }

  logout() {
    httpOptions.headers = httpOptions.headers.set('Authorization', ``);
    this.isLoggedIn = false;
    this.user = null;
    this.token = null;
  }
}
