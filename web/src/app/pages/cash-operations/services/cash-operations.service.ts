import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiService } from "src/app/core/service/api.service";

@Injectable({ providedIn: 'root' })
export class CashOperationsService extends ApiService<any> {

    static PATH = '/pdv/api/cash'
    protected readonly PATH = CashOperationsService.PATH;

    constructor(protected override httpClient: HttpClient) {
        super(httpClient);
    }
}
