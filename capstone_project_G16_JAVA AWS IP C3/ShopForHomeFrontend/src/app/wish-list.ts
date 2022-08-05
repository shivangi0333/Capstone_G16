import { Product } from "./product";
import { Users } from "./users";

export class WishList {
  public id: number;
  public user: Users;
  public product: Product;
  constructor(id:number=-1,user:Users=new Users,product:Product=new Product){
    this.id=id;
    this.user=user;
    this.product=product;
  }

}
