import {Component, OnInit} from '@angular/core';
import {Observable} from 'rxjs';
import {OrderService} from '../../services/order.service';
import {OrderModel} from '../../models/order.model';
import {ActivatedRoute} from '@angular/router';

@Component({
  selector: 'app-order-detail',
  templateUrl: './order-detail.component.html',
  styleUrls: ['./order-detail.component.css']
})
export class OrderDetailComponent implements OnInit {
  constructor(private orderService: OrderService, private route: ActivatedRoute) {
  }

  orderObservable: Observable<OrderModel>;

  ngOnInit() {
    this.orderObservable = this.orderService.show(this.route.snapshot.paramMap.get('id'));
  }

}
