import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort, MatSnackBar } from '@angular/material';
import { FoodService } from '../../services/food.service';
import { Food } from 'src/app/model/food';
import { OrderService } from 'src/app/services/order.service';
// import { Observable } from 'rxjs';
// import {DataSource} from '@angular/cdk/collections';
// import { BehaviorSubject } from 'rxjs';

// const ELEMENT_DATA: Food[] = [
//   {id: 1, name: 'Magyaros', description: 'szalámi, kukorica', vegetarian: false, spicy: true, price: 990},
//   {id: 2, name: 'Kolbászos', description: 'kolbász', vegetarian: true, spicy: true, price: 1990},
//   {id: 3, name: 'Bolognai', description: 'bolognai szósz', vegetarian: false, spicy: true, price: 1490},
//   {id: 4, name: 'Chilis', description: 'chilisbab',  vegetarian: true, spicy: false, price: 1290},
//   {id: 5, name: 'SonGo', description: 'sonka, gomba',  vegetarian: false, spicy: false, price: 1390},
//   {id: 6, name: 'Sonkás', description: 'sonka', vegetarian: true, spicy: true, price: 1290},
//   {id: 7, name: 'Gombás', description: 'gomba', vegetarian: true, spicy: false, price: 1890},
//   {id: 8, name: 'Csirkés', description: 'paradicsom, csirkemell', vegetarian: false, spicy: true, price: 990},
//   {id: 9, name: 'Kukoricás', description: 'kukorica', vegetarian: false, spicy: false, price: 890},
//   {id: 10, name: 'Olasz', description: 'sonka, szalámi', vegetarian: false, spicy: true, price: 1390},
// ];

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit {

  displayedColumns: string[] = ['name', 'description', 'vegetarian', 'spicy', 'price'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  applyFilter(filterValue: string) {
   this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  constructor(private foodService: FoodService, public snackBar: MatSnackBar, public orderService: OrderService) { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.foodService.getFood().subscribe(
      data => { this.dataSource.data = data; }
    );
    this.dataSource.filterPredicate = function(data: Food, filter: string): boolean {
      return ((data.name.includes(filter)) || (data.description.includes(filter)));
    };
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action, {
      duration: 1500,
    });
  }

  getRecord(row) {
    console.log(row);
    this.orderService.addFood(row);
    this.openSnackBar(row.name + ' sikeresen a kosárhoz adva!', '');
  }

}
