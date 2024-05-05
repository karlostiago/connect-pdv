import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { ApiService } from "src/app/core/service/api.service";
import { Cash } from "../models/cash.model";
import { ErroHandlerService } from "../core/service/error-handler.service";

@Injectable({ providedIn: 'root' })
export class CashOperationsService extends ApiService<any> {

    protected override pathURL(): string {
        return 'cash'
    }

    constructor(private httpClient: HttpClient,
        protected override error: ErroHandlerService) {
        super(error);
    }
    allOpeningCash(): Promise<Cash[]> { 
        const request = this.httpClient.get(`${this.baseURL}/${this.pathURL()}/all-cash`, this.options());
        return this.toPromise(request);
    }

    openCash(cash: Cash): Promise<Cash> {
        const request = this.httpClient.post(`${this.baseURL}/${this.pathURL()}/openCash`, JSON.stringify(cash), this.options());
        return this.toPromise(request);
    }
}
