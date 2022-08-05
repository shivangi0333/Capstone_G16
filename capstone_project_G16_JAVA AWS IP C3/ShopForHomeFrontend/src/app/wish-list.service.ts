import { Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Item } from "./Item";
import { BehaviorSubject, Observable, of } from "rxjs";
import { UsersService } from "./users.service";
import { WishList } from "./wish-list";
import { Users } from "./users";

@Injectable({
  providedIn: "root",
})
export class WishListService {

  private itemsSubject: BehaviorSubject<Item[]>;
  private totalSubject: BehaviorSubject<number>;
  public items: Observable<Item[]>;
  public total: Observable<number>;

  user:Users=new Users

  a : any;

  constructor(private http: HttpClient, private userService: UsersService) {
    this.itemsSubject = new BehaviorSubject<Item[]>(null);
    this.items = this.itemsSubject.asObservable();
    this.totalSubject = new BehaviorSubject<number>(null);
    this.total = this.totalSubject.asObservable();
    if(userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }
  }

  getPage(page = 1, size = 10): Observable<any> {
    return this.http.get("http://localhost:9123/api/v1/wishlist/list/"+this.user.email+"?page="+page+"&size="+size);
  }

  addToWishList(productId): Observable<WishList> {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }
    if (this.user.email) {
      if (productId) {
        return this.http.post<WishList>("http://localhost:9123/api/v1/wishlist/add/"+productId+"/"+this.user.email, null);
      }
    }
    return null;
  }

  removeFromWishList(productId: string): Observable<Boolean> {
    if(this.userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }
    if (this.user.email) {
      console.log("the executed");
      console.log(this.user.email)
      return this.http.delete<Boolean>("http://localhost:9123/api/v1/wishlist/delete/"+productId+"/"+this.user.email);
    } else {
      console.log("hi");
      return of(false);
    }
  }
}
