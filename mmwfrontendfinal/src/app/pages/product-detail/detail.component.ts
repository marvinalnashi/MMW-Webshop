import {Component, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute, Router} from '@angular/router';
import {CartService} from '../../services/cart.service';
import {CookieService} from 'ngx-cookie-service';
import {ProductinorderModel} from '../../models/productinorder.model';
import {ProductinfoModel} from '../../models/productinfo.model';

@Component({
  selector: 'app-detail',
  templateUrl: './detail.component.html',
  styleUrls: ['./detail.component.css']
})
export class DetailComponent implements OnInit {
  title: string;
  amount: number;
  productInfo: ProductinfoModel;

  constructor(private productService: ProductService, private cartService: CartService, private cookieService: CookieService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.getProduct();
    this.title = 'Product Details';
    this.amount = 1;
  }

  getProduct(): void {
    const id = this.route.snapshot.paramMap.get('id');
    this.productService.getDetail(id).subscribe(
      product => {
        this.productInfo = product;
      }
    );
  }

  addToCart() {
    this.cartService
      .addItem(new ProductinorderModel(this.productInfo, this.amount))
      .subscribe(
        order => {
          if (!order) {
            throw new Error();
          }
          this.router.navigateByUrl('/cart');
        }
      );
  }

  validateCount() {
    const stock = this.productInfo.productStock;
    if (this.amount > stock) {
      this.amount = stock;
    } else if (this.amount < 1) {
      this.amount = 1;
    }
  }
}
