import { Component, OnInit } from '@angular/core';
import { DiscountService } from '../discount.service';
import { NgForm } from '@angular/forms';
import { Discount } from '../discount';
import { Router } from '@angular/router';

@Component({
  selector: 'app-discount',
  templateUrl: './discount.component.html',
  styleUrls: ['./discount.component.css']
})
export class DiscountComponent implements OnInit {

  discounts: Array<Discount> = [];
  coupon : string;
  storeMsg: string="";
  constructor(public disc: DiscountService,private route:Router) { }

  ngOnInit(): void {
      this.loadDiscounts();
  }

  loadDiscounts(): void{
    this.disc.loadDiscount().subscribe(res => this.discounts=res);

  }
  storeDiscount(discountRef: NgForm) {
    console.log(discountRef)
    this.disc.storeDiscountDetails(discountRef.value).
      subscribe(res => this.storeMsg = res);

    this.loadDiscounts();
    this.route.navigateByUrl("/discount");
  }

  remove(discount:Discount){
      this.disc.removeDiscountDetails(discount).subscribe(res=> this.storeMsg=res);
      this.loadDiscounts();
    this.route.navigateByUrl("/discount");
  }
  
}
