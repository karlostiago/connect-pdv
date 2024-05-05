import { Component, Input, OnInit } from '@angular/core';
import { CashOperationsService } from '../../../../services/cash-operations.service';
import { Cash } from 'src/app/models/cash.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cash-register-list',
  templateUrl: './cash-register-list.component.html',
  styleUrl: './cash-register-list.component.scss'
})
export class CashRegisterListComponent implements OnInit {

  cashList: Cash[];
  showLoading: boolean = true;

  message = 'Carergando....';
  
  constructor(private cashOperationService: CashOperationsService, private router: Router) {

  }

  ngOnInit(): void {
    this.buscarOpeningCash();
  }

  buscarOpeningCash() {
    setTimeout(() => {
      this.cashOperationService.allOpeningCash().subscribe((data) => {
        this.cashList = data;
        this.showLoading = false;
        console.log(data);
      });
    }, 3000);
  }

  navigateToCashFlow(cashId: string) {
      this.router.navigate(['/cash-flow', cashId]);
  }

}
