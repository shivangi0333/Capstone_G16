import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject,Observable, of } from 'rxjs';
import { Users } from '../users';
import { UsersService } from '../users.service';
import { HttpClient } from "@angular/common/http";
import { ProductInOrder } from '../productInOrder';
import { Cart } from '../cart';
import { catchError, map, tap } from "rxjs/operators";
import { Item } from '../Item';
import { CookieService } from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private cartUrl = `$"http://localhost:9123/api/v1/cart`;
  localMap = {};

  private itemsSubject: BehaviorSubject<Item[]>;
  private totalSubject: BehaviorSubject<number>;
  public items: Observable<Item[]>;
  public total: Observable<number>;

  public search = new BehaviorSubject<string>("");
  grandTotal = 0;


  public wishList=new BehaviorSubject<any>([]);
  public wishListItem:any=[];
  public users: Users[];

  user:Users = new Users;
  cartItemList: any;
  productList: any;

  constructor(private userService : UsersService,private http: HttpClient,
    private cookieService: CookieService,) {
      this.itemsSubject = new BehaviorSubject<Item[]>(null);
      this.items = this.itemsSubject.asObservable();
      this.totalSubject = new BehaviorSubject<number>(null);
      this.total = this.totalSubject.asObservable();
    if(userService.isLoggedIn)
    {
        this.user.email = localStorage.getItem('email');
    }

   }


   private getLocalCart(): ProductInOrder[] {
    if (this.cookieService.check("cart")) {
      this.localMap = JSON.parse(this.cookieService.get("cart"));
      return Object.values(this.localMap);
    } else {
      this.localMap = {};
      return [];
    }
  }


  getCart(): Observable<ProductInOrder[]> {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }

    const localCart = this.getLocalCart();
    if (this.user.email) {
      if (localCart.length > 0) {
        return this.http.post<Cart>("http://localhost:9123/api/v1/cart/merge/"+this.user.email, localCart).pipe(
          tap(() => {
            this.clearLocalCart();
          }),
          map((cart) => cart.products),
          catchError(() => of([]))
        );
      } else {
        return this.http.get<Cart>("http://localhost:9123/api/v1/cart/get/"+this.user.email).pipe(
          map((cart) => cart.products),
          catchError(() => of([]))
        );
      }
    } else {
      return of(localCart);
    }
  }

  addItem(productInOrder): Observable<boolean> {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }

    console.log("starting"+this.user.email)
    if (!this.user.email) {
      if (this.cookieService.check("cart")) {
        this.localMap = JSON.parse(this.cookieService.get("cart"));
      }
      if (!this.localMap[productInOrder.productId]) {
        this.localMap[productInOrder.productId] = productInOrder;
      } else {
        this.localMap[productInOrder.productId].count += productInOrder.count;
      }
      this.cookieService.set("cart", JSON.stringify(this.localMap));
      return of(true);
    } else {
      console.log("add cart"+this.user.email);
      return this.http.post<boolean>("http://localhost:9123/api/v1/cart/add/"+this.user.email, {
        quantity: productInOrder.count,
        productId: productInOrder.productId,
      });
    }
  }

  update(productInOrder): Observable<ProductInOrder> {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }

    if (this.user.email) {
     // const url = `${this.cartUrl}/${productInOrder.productId}`;
     console.log("update called");
      return this.http.put<ProductInOrder>("http://localhost:9123/api/v1/cart/modify/"+productInOrder.productId+"/"+this.user.email, productInOrder.count);
    }
    else{
      return null;
    }
  }

  remove(productInOrder) {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }


    if (!this.user.email) {
      delete this.localMap[productInOrder.productId];
      return of(null);
    } else {
      console.log("delete called");
      return this.http.delete("http://localhost:9123/api/v1/cart/delete/"+productInOrder.productId+"/"+this.user.email);
    }
  }

  storeLocalCart() {
    this.cookieService.set("cart", JSON.stringify(this.localMap));
  }

  clearLocalCart() {
    this.cookieService.delete("cart");
    this.localMap = {};
  }

}






