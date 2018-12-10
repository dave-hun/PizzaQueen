import { Injectable } from '@angular/core';
import { Food } from '../model/food';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  list: Array<Food> = [];

  getFood(): Array<Food> {
    return this.list;
  }

  addFood(f: Food) {
    this.list.push(f);
  }

  removeFood(f: string[]) {
    // this.list.splice(this.list.indexOf(f[0]), 1);
    console.log('TODO: törölni a listaelemeket');
  }

  constructor() { }
}
