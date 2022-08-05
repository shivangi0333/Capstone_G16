import { Product } from "./product";

export class ProductInOrder {
  productId: string;
  productName: string;
  productPrice: number;
  productStock: number;
  productDescription: string;
  productUrl: string;
  categoryType: number;
  count: number;

  constructor(productInfo: Product,quantity=1){
    this.productId = productInfo.productId;
    this.productName = productInfo.productName;
    this.productPrice = productInfo.productPrice;
    this.productStock = productInfo.productStock;
    this.productDescription = productInfo.productDescription;
    this.productUrl = productInfo.productUrl;
    this.categoryType = productInfo.categoryType;
    this.count = quantity;
  }
}