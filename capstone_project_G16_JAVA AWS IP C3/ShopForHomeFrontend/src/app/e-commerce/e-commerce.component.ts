import { Component, OnInit } from '@angular/core';
import { Product } from '../product';
import { ProductService } from '../product.service';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';


@Component({
  selector: 'app-e-commerce',
  templateUrl: './e-commerce.component.html',
  styleUrls: ['./e-commerce.component.css']
})
export class ECommerceComponent implements OnInit {
  products: Array<Product> = [];
  storeMsg: string = ""
  deleteMsg: string = ""
  updateMsg: string = ""
  flag: boolean = false;
  pid: string="";
  price: number = 0;
  pname: String = "";
  category: number =0 ;
  url: String = "";
  stock : number =0;
  description : string="";
  status:number=0;
  temp : Product=new Product;
  dd: Date = new Date();
  constructor(public pser: ProductService,public router:Router) { } //DI for Service class

  //it is a life cycle or hook of component it will call after constructor
  //it call only one time

  ngOnInit(): void {
    this.loadProducts();
  }
  // Loading the Product details
  loadProducts(): void {
    this.pser.loadProductDetails().subscribe(res => this.products = res
    );
    console.log('pro', this.products)
  }
  //Storing the product details in the database
  storeProduct(productRef: NgForm) {
    console.log(productRef.value);
    this.pser.storeProductDetails(productRef.value).
      subscribe(res => this.storeMsg = res, error => console.log(error), () => this.loadProducts());
    alert("Product Added Successfully");
    this.router.navigateByUrl("ecommerce");
  }

  //Deleting the product details
  deleteProduct(pid: string) {
    this.pser.deleteProductDetails(pid).
      subscribe(res => this.deleteMsg = res, error => console.log(error), () => this.loadProducts())
  }

  // Updating the product details
  updateProduct(product: Product) {
    console.log(product);
    this.flag = true;
    this.pid = product.productId;
    this.price = product.productPrice;
    this.category = product.categoryType;
    this.pname = product.productName;
    this.url = product.productUrl;
    this.stock=product.productStock;
    this.description=product.productDescription;
  }
  updateProductPrice(productRef: NgForm) {
   // let product = { "productId": this.pid, "price": this.price, "pname": this.pname, "category": this.category, "url": this.url }
   // console.log(product);
    let product=productRef.value
    this.temp.productId=product.productId;
    this.temp.productName=product.productName;
    this.temp.productPrice=product.productPrice;
    this.temp.productDescription=this.description;
    this.temp.productStock=product.productStock;
    this.temp.productUrl=product.productUrl;
    this.temp.categoryType=product.categoryType
    this.pser.updateProductInfo(this.temp).subscribe(result => this.updateMsg = result, error => console.log(error),
      () => {
        this.loadProducts();
        alert("Product Updated Successfully");
        this.flag = false;
      })
  }
}
