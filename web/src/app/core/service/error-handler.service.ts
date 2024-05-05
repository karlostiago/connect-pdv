import {Injectable} from "@angular/core";
import { AlertService } from "./alert.service";

@Injectable({
    providedIn: 'root'
})
export class ErroHandlerService {

    constructor(private notification: AlertService) {
    }

    capturar(errorResponse: any) {
        if (errorResponse.status === 401) {
            this.notification.error('Não autorizado.');
        }
        else if (errorResponse.status === 403) {
            this.notification.error('Acesso não permitido.');
        }
        else {
            for (const erro of errorResponse.error) {

                if(erro.errorType === 'ERROR') {
                    this.notification.error(erro['mensagem']);
                } else if (erro.errorType === 'WARN') {
                    this.notification.warn(erro['mensagem']);
                } else if (erro.erroType === 'INFO') {
                    this.notification.info(erro['mensagem']);
                } else if (erro.erroType === 'SUCCESS') {
                    this.notification.sucess(erro['mensagem']);
                }
            }
        }
    }
}