import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort, MatSnackBar } from '@angular/material';
import { DrinkService } from '../../services/drink.service';
import { Drink } from 'src/app/model/drink';

@Component({
  selector: 'app-drink-list',
  templateUrl: './drink-list.component.html',
  styleUrls: ['./drink-list.component.css']
})
export class DrinkListComponent implements OnInit {

  displayedColumns: string[] = ['name', 'price'];
  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  applyFilter(filterValue: string) {
   this.dataSource.filter = filterValue.trim().toLowerCase();
  }
  constructor(private drinkService: DrinkService, public snackBar: MatSnackBar) { }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.drinkService.getDrink().subscribe(
      data => { this.dataSource.data = data; }
    );
    this.dataSource.filterPredicate = function(data: Drink, filter: string): boolean {
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
    this.openSnackBar(row.name + ' sikeresen a kosárhoz adva!', '');
  }

}
