import { Permission } from "./permission.model";
import { User } from "./user.model";

export class GroupsUser {
    id: number;
    groupName: string;
    description: string;
    users: User[];
    permissions: Permission[];
}