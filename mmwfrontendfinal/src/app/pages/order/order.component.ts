import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {OrderService} from '../../services/order.service';
import {OrderModel} from '../../models/order.model';
import {OrderStatus} from '../../enum/OrderStatus';
import {UserService} from '../../services/user.service';
import {JwtResponse} from '../../response/JwtResponse';
import {Subscription} from 'rxjs';
import {ActivatedRoute} from '@angular/router';
import {Role} from '../../enum/Role';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.css']
})
export class OrderComponent implements OnInit, OnDestroy {
  page: any;
  status = OrderStatus;
  currentUser: JwtResponse;
  role = Role;
  constructor(private httpClient: HttpClient, private orderService: OrderService, private userService: UserService, private route: ActivatedRoute) {
  }

  querySub: Subscription;

  ngOnInit() {
    this.currentUser = this.userService.currentUserValue;
    this.querySub = this.route.queryParams.subscribe(() => {
      this.update();
    });

  }

  update() {
    let nextPage = 1;
    let size = 10;
    if (this.route.snapshot.queryParamMap.get('page')) {
      nextPage = +this.route.snapshot.queryParamMap.get('page');
      size = +this.route.snapshot.queryParamMap.get('size');
    }
    this.orderService.getPage(nextPage, size).subscribe(page => this.page = page);
  }


  cancel(order: OrderModel) {
    this.orderService.cancel(order.orderId).subscribe(res => {
      if (res) {
        order.orderStatus = res.orderStatus;
      }
    });
  }

  finish(order: OrderModel) {
    this.orderService.finish(order.orderId).subscribe(res => {
      if (res) {
        order.orderStatus = res.orderStatus;
      }
    });
  }

  ngOnDestroy(): void {
    this.querySub.unsubscribe();
  }

}
