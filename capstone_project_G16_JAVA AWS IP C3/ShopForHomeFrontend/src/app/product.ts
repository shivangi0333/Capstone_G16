export class Product {
    public productId:string;
    public productName:string;
    public productPrice: number;
    public productUrl:string;
    public categoryType:number;
    public productDescription:string;
    public productStock:number;
    constructor(
        productId:string='',
        productName:string='',
        productPrice: number=-1,
        productUrl:string='',
        categoryType:number=-1,
        productDescription:string='',
        productStock:number=-1
    ){
        this.productId=productId;
        this.productName=productName;
        this.productPrice=productPrice;
        this.productStock=productStock;
        this.productUrl=productUrl;
        this.categoryType=categoryType;
        this.productDescription=productDescription;
    }
}
