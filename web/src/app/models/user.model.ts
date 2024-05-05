import { GroupsUser } from "./group-users.model";
import { Permission } from "./permission.model";
import { Person } from "./person.model";

export class User {
    id:number;
    userName:string;
    registerDate: string;
    person = new Person();
    groupUsers = new Array<GroupsUser>();
    permissions = new Array<Permission>();
}