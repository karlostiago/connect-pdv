import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiService } from "src/app/core/service/api.service";
import { User } from "../models/user.model";
import { ErroHandlerService } from "../core/service/error-handler.service";


@Injectable({ providedIn: 'root' })
export class UserOperationsService extends ApiService<any> {

    protected override pathURL(): string {
        return 'users';
    }

    constructor(private httpClient: HttpClient,
        protected override error: ErroHandlerService) {
        super(error);
    }

    getAllUsers(): Promise<User[]> {
        const request = this.httpClient.get(`${this.baseURL}/${this.pathURL()}/all-users`, this.options());
        return this.toPromise(request);
    }
}
