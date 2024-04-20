import { Component, OnInit } from '@angular/core';
import { Message, MessageService } from 'primeng/api';

@Component({
  selector: 'app-cash-register-opener',
  templateUrl: './cash-register-opener.component.html',
  styleUrls: ['./cash-register-opener.component.scss'],
  providers: [MessageService]
})
export class CashRegisterOpenerComponent  implements OnInit{
  saldoInicial: number | undefined;
  showLoading = false;

  messageLoading = 'Processando...';
  messages: Message[] | undefined;

  constructor(private messageService: MessageService) {}


  ngOnInit(): void {}

  confirmCashRegister() {
    this.showLoading = true;
    setTimeout(() => {
      this.showLoading = false;
      this.messageService.add({severity:'success', summary:'Caixa Aberto', detail:'Abertura de caixa confirmada.'});    
    }, 3000);
  }

  cancelCashRegister() {
    this.showLoading = true;
    setTimeout(() => {
      this.showLoading = false;
      this.messageService.add({severity:'info', summary:'Caixa Aberto', detail:'Abertura de caixa cancelada.'});    
    }, 3000);
  }
}
