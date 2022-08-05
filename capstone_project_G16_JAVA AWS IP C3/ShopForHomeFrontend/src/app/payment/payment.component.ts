import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Users } from '../users';
import { Router } from '@angular/router';


@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrls: ['./payment.component.css']
})
export class PaymentComponent implements OnInit {

  msg:string=""
  discount!: number;
  b !:boolean;
  user:Users=new Users;

    constructor(private http: HttpClient,private router:Router) {} 

  ngOnInit(): void {
  
    
  }
  payNow(){
    //console.log("Order Placed Successfully!!!");

        this.user.email=localStorage.getItem('token');
        this.discount = Number(localStorage.getItem('discount'));
        console.log(this.user.email);
        console.log(this.discount);
        console.log("execoted");
        this.http.post("http://localhost:9123/api/v1/sales/add/"+this.user.email+"/"+this.discount,{}).subscribe(res=>
          console.log(res)
        );
        console.log("this is called");
        this.msg="Order Placed Successfully!!!";
        alert("order placed successfull"); 
        this.router.navigateByUrl("products")
      }
    
  }

