import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Urls } from '../services/urls';
import { Drink } from '../model/drink';

@Injectable({
  providedIn: 'root'
})
export class DrinkService {

  private serviceUrl = Urls.FoodServiceUrl;

  constructor(private http: HttpClient) { }

  getDrink(): Observable<Drink[]> {
    return this.http.get<Drink[]>(this.serviceUrl);
  }
}
