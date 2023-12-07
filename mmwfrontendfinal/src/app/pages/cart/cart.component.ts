import {AfterContentChecked, Component, OnDestroy, OnInit} from '@angular/core';
import {CartService} from '../../services/cart.service';
import {Subject, Subscription} from 'rxjs';
import {UserService} from '../../services/user.service';
import {JwtResponse} from '../../response/JwtResponse';
import {ProductinorderModel} from '../../models/productinorder.model';
import {debounceTime, switchMap} from 'rxjs/operators';
import {ActivatedRoute, Router} from '@angular/router';
import {Role} from '../../enum/Role';
import * as confetti from 'canvas-confetti';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit, OnDestroy, AfterContentChecked {
  constructor(private cartService: CartService, private userService: UserService, private router: Router) {
    this.userSubscription = this.userService.currentUser.subscribe(user => this.currentUser = user);
  }

  cartProducts = [];
  total = 0;
  currentUser: JwtResponse;
  userSubscription: Subscription;

  private subject = new Subject<ProductinorderModel>();
  sub: Subscription;

  static checkAmount(selectedProducts) {
    const stock = selectedProducts.productStock;
    if (selectedProducts.count > stock) {
      selectedProducts.count = stock;
    } else if (selectedProducts.count < 1) {
      selectedProducts.count = 1;
    }
  }

  ngOnInit() {
    this.cartService.getCart().subscribe(products => {
      this.cartProducts = products;
    });
    this.sub = this.subject.pipe(
      debounceTime(300),
      switchMap((productInOrder: ProductinorderModel) => this.cartService.update(productInOrder))
    ).subscribe(product => {
      if (product) { throw new Error(); }
    });
  }

  ngOnDestroy() {
    if (!this.currentUser) {
      this.cartService.storeLocalCart();
    }
    this.userSubscription.unsubscribe();
  }

  ngAfterContentChecked() {
    this.total = this.cartProducts.reduce(
      (previous, current) => previous + current.count * current.productPrice, 0);
  }

  addToAmount(productInOrder) {
    productInOrder.count++;
    CartComponent.checkAmount(productInOrder);
    if (this.currentUser) { this.subject.next(productInOrder); }
  }

  removeFromAmount(productInOrder) {
    productInOrder.count--;
    CartComponent.checkAmount(productInOrder);
    if (this.currentUser) { this.subject.next(productInOrder); }
  }

  onChange(productInOrder) {
    CartComponent.checkAmount(productInOrder);
    if (this.currentUser) { this.subject.next(productInOrder); }
  }

  removeFromOrder(productInOrder: ProductinorderModel) {
    this.cartService.remove(productInOrder).subscribe(
      () => {
        this.cartProducts = this.cartProducts.filter(e => e.productId !== productInOrder.productId);
      });
  }

  checkoutCurrentCart() {
      if (!this.currentUser) {
        this.router.navigate(['/login'], {queryParams: {returnUrl: this.router.url}});
      } else if (this.currentUser.role !== Role.Customer) {
        this.router.navigate(['/seller']);
      } else {
        confetti.create(undefined, { resize: true, useWorker: true })({
          particleCount: 200,
          spread: 200,
          origin: { y: 0.6 }});
        setTimeout(() => {
        this.cartService.checkout().subscribe(
          _ => {
            this.cartProducts = [];
          });
        this.router.navigate(['/']);
        },2000);
      }
  }




}

