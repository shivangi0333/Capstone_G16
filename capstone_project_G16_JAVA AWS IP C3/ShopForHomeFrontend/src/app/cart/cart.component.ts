import { Component, OnDestroy, OnInit } from '@angular/core';
import { Subject, Subscription } from 'rxjs';
import { CartService } from 'src/app/service/cart.service';

import { ProductInOrder } from '../productInOrder';
import { Users } from '../users';
import { UsersService } from '../users.service';
import { debounceTime, switchMap } from "rxjs/operators";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit,OnDestroy {

  productInOrders = [];
  public grandTotal : number=0;
  //public finalTotal !: number;
  user:Users=new Users;
  private updateTerms = new Subject<ProductInOrder>();
  sub: Subscription;

  constructor(private cartService: CartService,private userService:UsersService) { 
    if(userService.isLoggedIn)
    {
        this.user.email = localStorage.getItem('email');
    }
  }

  ngOnInit(): void {
    this.cartService.getCart().subscribe((prods) => {
      this.productInOrders = prods;
      console.log(this.productInOrders);
      this.productInOrders.forEach((product:ProductInOrder)=> {
        this.grandTotal += (product.count)*(product.productPrice);
      });
    });
    this.sub = this.updateTerms
      .pipe(
        debounceTime(300),
        switchMap((productInOrder: ProductInOrder) =>
          this.cartService.update(productInOrder)
        )
      )
      .subscribe();
  }

  static validateCount(productInOrder) {
    const max = productInOrder.productStock;
    if (productInOrder.count > max) {
      productInOrder.count = max;
    } else if (productInOrder.count < 1) {
      productInOrder.count = 1;
    }
  }


  getTotalPrice()
  {
    this.grandTotal=0;
    this.productInOrders.forEach((product:ProductInOrder)=> {
      this.grandTotal += (product.count)*(product.productPrice);
    });
  }

  inc(productInOrder) {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }
      productInOrder.count++;
      CartComponent.validateCount(productInOrder);
      if (this.user.email) {
        this.updateTerms.next(productInOrder);
      }
      this.getTotalPrice();
    }

  // Decreasing the Quantity of the products

  des(productInOrder: any) {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }
    productInOrder.count--;
    CartComponent.validateCount(productInOrder);
    if (this.user.email) {
      this.updateTerms.next(productInOrder);
    }
    this.getTotalPrice();
  }
 

  remove(productInOrder: ProductInOrder) {
    this.cartService.remove(productInOrder).subscribe(() => {
      this.productInOrders = this.productInOrders.filter(
        (e) => e.productId !== productInOrder.productId
      );
    });
  }

  ngOnDestroy() {
    if (!this.user.email) {
      this.cartService.storeLocalCart();
    }
    this.user.email='';
  }

}

