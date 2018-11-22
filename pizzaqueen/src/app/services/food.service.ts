import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Food } from '../model/food';
import { Urls } from '../services/urls';

@Injectable({
  providedIn: 'root'
})
export class FoodService {

  private serviceUrl = Urls.FoodServiceUrl;

  constructor(private http: HttpClient) { }

  getFood(): Observable<Food[]> {
    return this.http.get<Food[]>(this.serviceUrl);
  }
}
