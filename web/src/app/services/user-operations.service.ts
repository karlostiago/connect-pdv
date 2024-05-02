import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ApiService } from "src/app/core/service/api.service";
import { User } from "../models/user.model";


@Injectable({ providedIn: 'root' })
export class UserOperationsService extends ApiService<any> {

    static PATH = '/connect-pdv/pdv/api/users';
    protected readonly PATH = UserOperationsService.PATH;

    constructor(protected override httpClient: HttpClient) {
        super(httpClient);
    }

    getAllUsers(): Observable<User[]> { 
        return this.get('all-users');
    }
}
