import {Injectable} from '@angular/core';
import {MessageService} from "primeng/api";

@Injectable({
    providedIn: 'root'
})
export class AlertService {

    constructor(private messageService: MessageService) {
    }

    sucess(mensagem: string) {
        this.generateMessage(mensagem, 'success', 'Sucesso');
    }

    error(mensagem: string) {
        this.generateMessage(mensagem, 'error', 'Error');
    }

    warn(mensagem: string) {
        this.generateMessage(mensagem, 'warn', 'Atenção');
    }

    info(mensagem: string) {
        this.generateMessage(mensagem, 'info', 'Informação')
    }

    private generateMessage(message: string, severity: string, sumary: string) {
        this.messageService.add({
            severity: severity,
            summary: sumary,
            detail: message
        });
    }
}