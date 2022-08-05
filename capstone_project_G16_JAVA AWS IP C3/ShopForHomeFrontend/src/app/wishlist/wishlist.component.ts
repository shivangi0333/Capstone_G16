import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';
import { WishList } from '../wish-list';
import { WishListService } from '../wish-list.service';
import { ActivatedRoute } from '@angular/router';
import { Subscription } from 'rxjs';
import { Users } from '../users';
import { UsersService } from '../users.service';

@Component({
  selector: 'app-wishlist',
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishlistComponent implements OnInit {

  page: any;
  querySub: Subscription;

  private user : Users = new Users;

  public products: any = [];
  constructor(private cartService: CartService,private wishListService: WishListService,private route: ActivatedRoute,userService: UsersService) {

    if(userService.isLoggedIn)
    {
      this.user.email=localStorage.getItem('token');
    }
   }

  ngOnInit(): void {
    this.querySub = this.route.queryParams.subscribe(() => {
      this.update();
    });
  }

  
  update() {
    let nextPage = 1;
    let size = 10;
    if (this.route.snapshot.queryParamMap.get("page")) {
      nextPage = +this.route.snapshot.queryParamMap.get("page");
      size = +this.route.snapshot.queryParamMap.get("size");
    }

    this.wishListService
      .getPage(nextPage, size)
      .subscribe((page) => (this.page = page));
  }
  
  handleRemoveFromWishList(productId: string) {
    console.log(productId);
    this.wishListService.removeFromWishList(productId).subscribe((res) => {
      this.update();
      console.log(res);
    });
  }

}
