export class Users{
    public id:number;
    public name:string;
    public email:string;
    public password:string;

    constructor(
        id : number=-1,
         name : string='',
         email : string='',
         password : string=''
    ){

        this.id=id;
        this.name=name;
        this.email=email;
        this.password=password;
    }

}