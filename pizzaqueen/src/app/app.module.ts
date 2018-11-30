import { HttpClientModule } from '@angular/common/http';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {FlexLayoutModule} from '@angular/flex-layout';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './components/app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatToolbarModule, MatIconModule, MatButtonModule, MatTabsModule, MatTableModule, MatFormFieldModule, MatInputModule,
  MatPaginatorModule, MatCheckboxModule, MatSortModule } from '@angular/material';

import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { NavigationBarComponent } from './components/navigation-bar/navigation-bar.component';
import { MenuComponent } from './components/menu/menu.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { FoodListComponent } from './components/food-list/food-list.component';
import { DrinkListComponent } from './components/drink-list/drink-list.component';

import { FoodService } from './services/food.service';
import { DrinkService } from './services/drink.service';

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
    FoodService,
    DrinkService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
