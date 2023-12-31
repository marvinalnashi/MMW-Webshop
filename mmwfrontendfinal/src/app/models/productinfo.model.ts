import {ProductinorderModel} from './productinorder.model';

export class ProductinfoModel {
  productId: string;
  productName: string;
  productPrice: number;
  productStock: number;
  productDescription: string;
  productIcon: string;
  productStatus: number;
  categoryType: number;
  createTime: string;
  updateTime: string;


  constructor(productInOrder?: ProductinorderModel) {
    if (productInOrder) {
      this.productId = productInOrder.productId;
      this.productName = productInOrder.productName;
      this.productPrice = productInOrder.productPrice;
      this.productStock = productInOrder.productStock;
      this.productDescription = productInOrder.productDescription;
      this.productIcon = productInOrder.productIcon;
      this.categoryType = productInOrder.categoryType;
      this.productStatus = 0;
    } else {
      this.productId = '';
      this.productName = '';
      this.productPrice = 20;
      this.productStock = 100;
      this.productDescription = '';
      this.productIcon = '';
      this.categoryType = 0;
      this.productStatus = 0;
    }
  }
}
