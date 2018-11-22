import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';

export interface Food {
  name: string;
  description: string;
  vegetarian: boolean;
  spicy: boolean;
  price: number;
}

const ELEMENT_DATA: Food[] = [
  {name: 'Magyaros', description: 'szalámi, kukorica', vegetarian: false, spicy: true, price: 990},
  {name: 'Kolbászos', description: 'kolbász', vegetarian: true, spicy: true, price: 1990},
  {name: 'Bolognai', description: 'bolognai szósz', vegetarian: false, spicy: true, price: 1490},
  {name: 'Chilis', description: 'chilisbab',  vegetarian: true, spicy: false, price: 1290},
  {name: 'SonGo', description: 'sonka, gomba',  vegetarian: false, spicy: false, price: 1390},
  {name: 'Sonkás', description: 'sonka', vegetarian: true, spicy: true, price: 1290},
  {name: 'Gombás', description: 'gomba', vegetarian: true, spicy: false, price: 1890},
  {name: 'Csirkés', description: 'paradicsom, csirkemell', vegetarian: false, spicy: true, price: 990},
  {name: 'Kukoricás', description: 'kukorica', vegetarian: false, spicy: false, price: 890},
  {name: 'Olasz', description: 'sonka, szalámi', vegetarian: false, spicy: true, price: 1390},
];

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit {

  displayedColumns: string[] = ['name', 'description', 'vegetarian', 'spicy', 'price'];
  dataSource = new MatTableDataSource<Food>(ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  constructor() { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.dataSource.filterPredicate = function(data, filter: string): boolean {
      return data.name.includes(filter) || data.description.includes(filter);
    };
  }

}
