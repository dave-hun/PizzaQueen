import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator } from '@angular/material';

export interface PeriodicElement {
  name: string;
  position: number;
  weight: number;
  symbol: string;
  plus: string;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', weight: 1.0079, symbol: 'H', plus: 'cica'},
  {position: 2, name: 'Helium', weight: 4.0026, symbol: 'He', plus: 'kutya'},
  {position: 3, name: 'Lithium', weight: 6.941, symbol: 'Li', plus: 'pingvin'},
  {position: 4, name: 'Beryllium', weight: 9.0122, symbol: 'Be', plus: 'beka'},
  {position: 5, name: 'Boron', weight: 10.811, symbol: 'B', plus: 'harcsa'},
  {position: 6, name: 'Carbon', weight: 12.0107, symbol: 'C', plus: 'tigris'},
  {position: 7, name: 'Nitrogen', weight: 14.0067, symbol: 'N', plus: 'cica'},
  {position: 8, name: 'Oxygen', weight: 15.9994, symbol: 'O', plus: 'cica'},
  {position: 9, name: 'Fluorine', weight: 18.9984, symbol: 'F', plus: 'cica'},
  {position: 10, name: 'Neon', weight: 20.1797, symbol: 'Ne', plus: 'cica'},
];

@Component({
  selector: 'app-food-list',
  templateUrl: './food-list.component.html',
  styleUrls: ['./food-list.component.css']
})
export class FoodListComponent implements OnInit {

  displayedColumns: string[] = ['position', 'name', 'weight', 'symbol', 'plus'];
  dataSource = new MatTableDataSource<PeriodicElement>(ELEMENT_DATA);

  @ViewChild(MatPaginator) paginator: MatPaginator;

  applyFilter(filterValue: string) {
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  constructor() { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

}
