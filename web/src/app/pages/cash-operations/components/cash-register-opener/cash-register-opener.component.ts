import { Component, OnInit } from '@angular/core';
import { Message, MessageService } from 'primeng/api';
import { User } from 'src/app/models/user.model';
import { Cash } from 'src/app/models/cash.model';
import { UserOperationsService } from 'src/app/services/user-operations.service';
import { CashOperationsService } from 'src/app/services/cash-operations.service';
import { CashType } from 'src/app/enums/cash.type';

@Component({
  selector: 'app-cash-register-opener',
  templateUrl: './cash-register-opener.component.html',
  styleUrls: ['./cash-register-opener.component.scss'],
  providers: [MessageService]
})
export class CashRegisterOpenerComponent implements OnInit {

  showLoading = false;
  messageLoading = 'Processando...';
  

  messages: Message[] = [];
  cash: Cash = new Cash();

  users: User[] = [];
  selectedUserId: number | null = null;

  cashTypes = new Array<String>();
  items: any = [];

  constructor(
    private messageService: MessageService,
    private userOperationsService: UserOperationsService,
    private cashOperationsService: CashOperationsService
  ) {}

  ngOnInit(): void {
    this.loadUsers();
    this.loadCashTypes();
    this.loadItems();
  }

  loadUsers(): void {
    this.showLoading = true;
    this.userOperationsService.getAllUsers().subscribe({
      next: (users: User[]) => {
        this.users = users;
        this.showLoading = false;
      },
      error: () => {
        this.showLoading = false;
      }
    });
  }

  confirmCashRegister(): void {

    if (this.selectedUserId !== null) {
      const selectedUser = this.users.find(user => user.id === this.selectedUserId);

      if (selectedUser) {

        this.cash.user = selectedUser;
        this.showLoading = true;

        this.cashOperationsService.openCash(this.cash).subscribe({

          next: () => {
            this.showLoading = false;
            this.messageService.add({ severity: 'success', summary: 'Operação executada com sucesso:', detail: 'Abertura de caixa.' });
          },
          error: () => {
            this.showLoading = false;
            this.messageService.add({ severity: 'error', summary: 'Erro ao efetuar a operação:', detail: 'Abertura de caixa.' });
          }
        });
      }}
  }

  cancelCashRegister(): void {
    this.messageService.add({ severity: 'info', summary: 'Operação cancelada:', detail: 'Abertura de caixa.' });
  }

  private loadCashTypes() {
    for (const cashTypeKey in CashType) {
        
        this.cashTypes.push(CashType[`${cashTypeKey}`].toUpperCase().toString());
    }
  }

  private loadItems() {
    this.items = [
      {label: 'Caixas', routerLink: '/cash-operations/cash-register-list'},
      {label: 'Abertura de caixa', routerLink: '/cash-operations/cash-register-opener'}
    ];
  }

}
