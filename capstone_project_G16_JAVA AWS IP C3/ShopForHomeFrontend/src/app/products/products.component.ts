import { Component, OnInit } from '@angular/core';
import { ApiService } from 'src/app/service/api.service';
import { CartService } from 'src/app/service/cart.service';
import { ProductInOrder } from '../productInOrder';
import { ActivatedRoute, Router } from "@angular/router";
import { WishListService } from '../wish-list.service';
import { UsersService } from '../users.service';

@Component({
  selector: 'app-products',
  templateUrl: './products.component.html',
  styleUrls: ['./products.component.css']

})
export class ProductsComponent implements OnInit {

  addedToWishlist: boolean = false;
  
  public productList: any;
  public filterCategory: any
  searchKey: string = "";
  categoryType: number=-1;
  constructor(private api: ApiService, private cartService: CartService, private router: Router,private wishListService:WishListService,public userService:UsersService) { }

  ngOnInit(): void {
    this.api.getProduct()
      .subscribe(res => {
        this.productList = res;
        this.filterCategory = res;
        this.productList.forEach((a: any) => {
          if (a.categoryType === 0 || a.categoryType === 1|| a.categoryType === 2 || a.categoryType === 3 || a.categoryType === 4 || a.categoryType === 5 || a.categoryType === 6 || a.categoryType === 7) { }
          Object.assign(a, { quantity: 1, total: a.productPrice });
        });
        console.log(this.productList);
      });
    this.cartService.search.subscribe((val: any) => {
      this.searchKey = val;
    })

  }


  //Adding products into cart
  addtocart(product: any) {
    //this.cartService.addtoCart(product);
    if(!this.userService.isLoggedIn())
    {
      alert("login to add the products in to the cart");
      this.router.navigateByUrl("userlogin");
    }
    else{
    this.cartService
      .addItem(new ProductInOrder(product, 1))
      .subscribe(() => {
        this.router.navigateByUrl("/");
      });
      console.log("completed");
    }
  }
  //filtering the products
  filter(category: string) {
    if(category=='Sofa')
    {
      this.categoryType=0;
    }
    else if(category=='Tables')
    {
      this.categoryType=1;
    }
    else if(category=='Fashion')
    {
      this.categoryType=2;
    }
    else if(category=='Mobiles')
    {
      this.categoryType=3;
    }
    else if(category=='Electronics')
    {
      this.categoryType=4;
    }
    else if(category=='Appliances')
    {
      this.categoryType=5;
    }
    else if(category=='Beauty')
    {
      this.categoryType=6;
    }
    else if(category=='Sports')
    {
      this.categoryType=7;
    }
    else{
      this.categoryType=-1
    }

    this.filterCategory = this.productList.filter((a: any) => {
      if (a.categoryType == this.categoryType || this.categoryType == -1) {
        return a;
      }
    })
  }

  handleAddToWishList(productId) {
    if(!this.userService.isLoggedIn())
    {
      alert("login to add the products in to the wishlist");
      this.router.navigateByUrl("userlogin");
    }
    else{
    this.wishListService.addToWishList(productId).subscribe(() => {
      this.addedToWishlist = true;
    });
  }
  }

  handleRemoveFromWishList(productId) {
    this.wishListService.removeFromWishList(productId).subscribe(() => {
      this.addedToWishlist = false;
    });
  }

}
