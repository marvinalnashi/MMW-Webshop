import {ProductinfoModel} from './productinfo.model';

export class ProductinorderModel {
  productId: string;
  productName: string;
  productPrice: number;
  productStock: number;
  productDescription: string;
  productIcon: string;
  categoryType: number;
  count: number;

  constructor(productInfo: ProductinfoModel, quantity = 1) {
    this.productId = productInfo.productId;
    this.productName = productInfo.productName;
    this.productPrice = productInfo.productPrice;
    this.productStock = productInfo.productStock;
    this.productDescription = productInfo.productDescription;
    this.productIcon = productInfo.productIcon;
    this.categoryType = productInfo.categoryType;
    this.count = quantity;
  }
}
