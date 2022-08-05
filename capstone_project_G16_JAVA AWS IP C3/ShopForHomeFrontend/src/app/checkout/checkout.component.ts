import { Component, OnInit } from '@angular/core';
import { CartService } from '../service/cart.service';
import { DiscountService } from '../discount.service';
import { ProductInOrder } from '../productInOrder';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit {
  productInOrders = [];
  public grandTotal : number=0;
  public code!: string;
  // public coupons: string[] = ["RKM10","SRJ20","DEB15","PAV30"];
  public coupons:string[]=[];  
  public percent:number[]=[];
  msg:string="";
  discount1:number=0;
  final1!:number;

  constructor(private cartService: CartService,private discountService:DiscountService) { }

  ngOnInit(): void {
    this.cartService.getCart().subscribe((prods) => {
      this.productInOrders = prods;
      console.log(this.productInOrders);
      this.productInOrders.forEach((product:ProductInOrder)=> {
        this.grandTotal += (product.count)*(product.productPrice);
      });
  });
  localStorage.setItem("discount","0");
}

  applyCoupon(){
    this.discountService.loadDiscount().subscribe(res=>{
      for(let c of res){
             this.coupons.push(c.dname);
             this.percent.push(c.dpercent);
      }
    },error=>{
      console.log("error is"+error)
    })

    console.log("the coupons is"+this.coupons)
    
    for(var val of this.coupons){
      if(val==this.code){
        this.discount1=this.percent[this.coupons.indexOf(val)];
        this.msg="discount of "+this.discount1+"%";
        localStorage.setItem("discount",''+this.discount1);
        this.final1=this.grandTotal-this.grandTotal*(this.discount1/100);
      }
      else{
        localStorage.setItem("discount",''+this.discount1);
       this.msg="invalid coupon code"
      }
    }

  }

  
}


