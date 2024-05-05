import { Component, OnInit } from '@angular/core';
import { Message, MessageService } from 'primeng/api';
import { User } from 'src/app/models/user.model';
import { Cash } from 'src/app/models/cash.model';
import { UserOperationsService } from 'src/app/services/user-operations.service';
import { CashOperationsService } from 'src/app/services/cash-operations.service';
import { CashType } from 'src/app/enums/cash.type';
import { AlertService } from 'src/app/core/service/alert.service';
import { ErroHandlerService } from 'src/app/core/service/error-handler.service';

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

  showAgencyInput = false;
  showAccountInput = false;

  constructor(
    private messageService: MessageService,
    private userOperationsService: UserOperationsService,
    private cashOperationsService: CashOperationsService,
    private alertService: AlertService,
    private errorHandler: ErroHandlerService
  ) {}

  ngOnInit(): void {
    this.loadUsers();
    this.loadCashTypes();
    this.loadItems();
  }


  private loadCashTypes() {
    for (const cashTypeKey in CashType) {
        this.cashTypes.push(CashType[`${cashTypeKey}`].toUpperCase());
    }
  }

  private loadItems() {
    this.items = [
      {label: 'Caixas', routerLink: '/cash-operations/cash-register-list'},
      {label: 'Abertura de caixa', routerLink: '/cash-operations/cash-register-opener'}
    ];
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
    if (this.selectedUserId !== null && this.users.some(user => user.id === this.selectedUserId)) {
      
        const selectedUser = this.users.find(user => user.id === this.selectedUserId);

        this.cash.user = selectedUser;
        this.showLoading = true;

        this.cashOperationsService.openCash(this.cash).subscribe({
            next: () => {
                this.showLoading = false;
                this.alertService.sucess('Operação executada com sucesso!');
            },
            error: (errorMessage: ErroHandlerService ) => {
                this.showLoading = false;
                this.errorHandler.capturar(errorMessage);
            }
        });
    }
  }

  showAccountAndAgency() {
    this.showAgencyInput = this.cash.types === 'BANCO';
    this.showAccountInput = this.cash.types === 'BANCO';
  }

  cancelCashRegister(): void {
    this.messageService.add({ severity: 'info', summary: 'Operação cancelada:', detail: 'Abertura de caixa.' });
  }

  areFieldsValid(): boolean {
    if (this.cash.types === 'BANCO') {
        return !!this.selectedUserId && !!this.cash.openingValue && !!this.cash.agency && !!this.cash.account;
    } else {
        return !!this.selectedUserId && !!this.cash.openingValue;
    }
  }
}
