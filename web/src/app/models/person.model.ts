import { Address } from "./address.model"

export class Person {
    id: number;
    document: string;
    dateOfBirth: string;
    dateRegister: string;
    address = new Address();
    name: string;
    nick: string;
    observation: string;
    contact: string;

}