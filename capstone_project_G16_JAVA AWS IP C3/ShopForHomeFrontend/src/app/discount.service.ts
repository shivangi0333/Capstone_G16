import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Discount } from './discount';

@Injectable({
  providedIn: 'root'
})
export class DiscountService {

  constructor(public http: HttpClient) { }

  loadDiscount():Observable<Discount[]>{
    return this.http.get<Discount[]>("http://localhost:9123/api/v1/admin/discount")
  }
  
  storeDiscountDetails(discount: Discount): Observable<string>{
    return this.http.post("http://localhost:9123/api/v1/admin/addDiscount", discount,{ responseType: 'text' })
  }
  
  removeDiscountDetails(discount:Discount): Observable<string>{
    console.log(discount.dname)
    return this.http.delete<string>("http://localhost:9124/discount/delete/"+discount.did)
  }
}
