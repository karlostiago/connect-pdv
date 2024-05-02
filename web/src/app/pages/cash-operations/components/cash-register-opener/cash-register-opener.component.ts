import { Component, OnInit } from '@angular/core';
import { Message, MessageService } from 'primeng/api';
import { CashType } from '../../../../enums/cash.type';
import { CashTypeItem } from '../../../../interfaces/cash.type.interface';
import { UserOperationsService } from 'src/app/services/user-operations.service';
import { User } from 'src/app/models/user.model';
@Component({
  selector: 'app-cash-register-opener',
  templateUrl: './cash-register-opener.component.html',
  styleUrls: ['./cash-register-opener.component.scss'],
  providers: [MessageService]
})
export class CashRegisterOpenerComponent  implements OnInit{
  initialbalance: number | undefined;
  showLoading = false;

  messageLoading = 'Processando...';
  messages: Message[] | undefined;

  cashTypes: CashTypeItem[] = Object.keys(CashType).map(key => ({ description: CashType[key], value: key as CashType }));
  selectedCashType: CashType | undefined;

  selectedUser: User | undefined;
  items: any = [];

  userId: number;
  users: User[] = [];

  constructor(private messageService: MessageService, private userOperationsService: UserOperationsService) {}


  ngOnInit(): void {
    this.items = [
      {label: 'Caixas', routerLink: '/cash-operations/cash-register-list'},
      {label: 'Abertura de caixa', routerLink: '/cash-operations/cash-register-opener'}
    ];

    this.loadUsers();
     
  }

  confirmCashRegister() {
    this.showLoading = true;
    setTimeout(() => {
      this.showLoading = false;
      this.messageService.add({severity:'success', summary:'Operação executada com sucesso:', detail:'Abertura de caixa.'});    
    }, 3000);
  }
 
  cancelCashRegister() {
    this.showLoading = true;
    setTimeout(() => {
      this.showLoading = false;
      this.messageService.add({severity:'info', summary:'Operação cancelada:', detail:'Abertura de caixa.'});    
    }, 3000);
  }

  loadUsers(): void {
    this.userOperationsService.getAllUsers().subscribe({
      next: (users: User[]) => {
        this.users = users;
      },
      error: (error) => {
        console.error('Error loading users:', error);
        this.messageService.add({severity:'error', summary:'Erro ao efetuar a operação:', detail:'Abertura de caixa.'});
      }
    });
  }
}
