import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatIconModule, MatButtonModule, MatTabsModule, MatTableModule, MatFormFieldModule, MatInputModule,
  MatPaginatorModule, MatCheckboxModule, MatSortModule } from '@angular/material';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NavigationBarComponent } from './navigation-bar/navigation-bar.component';
import { MenuComponent } from './menu/menu.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { FoodListComponent } from './food-list/food-list.component';
import { DrinkListComponent } from './drink-list/drink-list.component';

import { FoodService } from './services/food.service';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    MenuComponent,
    HomeComponent,
    LoginComponent,
    FoodListComponent,
    DrinkListComponent,
  ],
  imports: [
    FlexLayoutModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatTabsModule,
    MatTableModule,
    MatFormFieldModule,
    MatInputModule,
    MatPaginatorModule,
    MatCheckboxModule,
    MatSortModule,
    HttpClientModule,
    NgbModule.forRoot()
  ],
  providers: [
    FoodService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
