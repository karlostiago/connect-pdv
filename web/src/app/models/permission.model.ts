import { GroupsUser } from "./group-users.model";
import { User } from "./user.model";

export class Permission {
    id: number;
    name: string;
    description: string;
    users: User[];
    userGroups: GroupsUser[];
}