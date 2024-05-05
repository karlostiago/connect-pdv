import { User } from "./user.model";

export class Cash {
    id: number;
    description:string;
    openingValue:number;
    totalValue:number;
    closingValue:number;
    entryValue:number;
    exitValue:number;
    registerDate:string;
    closingDate:string;
    types :string;
    agency:string
    account:string
    user = new User();
}