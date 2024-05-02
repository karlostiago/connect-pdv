import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { ApiService } from "src/app/core/service/api.service";
import { Cash } from "../models/cash.model";

@Injectable({ providedIn: 'root' })
export class CashOperationsService extends ApiService<any> {

    static PATH = '/connect-pdv/pdv/api/cash';
    protected readonly PATH = CashOperationsService.PATH;

    constructor(protected override httpClient: HttpClient) {
        super(httpClient);
    }

    allOpeningCash(): Observable<Cash[]> { 
        return this.get('opening-cash');
    }
}
