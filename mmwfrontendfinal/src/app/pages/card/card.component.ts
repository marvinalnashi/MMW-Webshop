import {Component, OnDestroy, OnInit} from '@angular/core';
import {ProductService} from '../../services/product.service';
import {ActivatedRoute} from '@angular/router';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit, OnDestroy {
  title: string;
  page: any;
  private param: Subscription;
  private query: Subscription;

  constructor(private productService: ProductService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.query = this.route.queryParams.subscribe(() => {
      this.update();
    });
    this.param = this.route.params.subscribe(() => {
      this.update();
    });
  }

  ngOnDestroy(): void {
    this.query.unsubscribe();
    this.param.unsubscribe();
  }

  update() {
    if (this.route.snapshot.queryParamMap.get('page')) {
      const currentPage = +this.route.snapshot.queryParamMap.get('page');
      const size = +this.route.snapshot.queryParamMap.get('size');
      this.getProducts(currentPage, size);
    } else {
      this.getProducts();
    }
  }

  getProducts(page: number = 1, size: number = 4) {
    if (this.route.snapshot.url.length === 1) {
      this.productService.getAllInPage(+page, +size)
        .subscribe(page => {
          this.page = page;
          this.title = '';
        });
    } else {
      const categoryType = this.route.snapshot.url[1].path;
      this.productService.getCategoryInPage(+categoryType, page, size)
        .subscribe(selectedCategory => {
          this.title = selectedCategory.category;
          this.page = selectedCategory.page;
        });
    }
  }
}
