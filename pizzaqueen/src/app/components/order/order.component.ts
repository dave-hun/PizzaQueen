import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit {

  constructor(public authService: AuthService,
    private router: Router, public orderService: OrderService) { }

    orderlist = this.orderService.getFood();
    /* {
      name: string;
    }[] = [
      {
        name: 'Egy'
      },
      {
        name: 'Nyolc'
      },
      {
        name: 'Area 3'
      },
    ];*/

    selectedOptions: string[] = [];

    ngOnInit() {
    }

    onNgModelChange(event) {
      console.log('Lista módosítva: ', event);
      this.orderService.removeFood(event);
    }

}
