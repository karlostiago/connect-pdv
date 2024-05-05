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
  
  constructor(private cashOperationService: CashOperationsService, private router: Router) {}

  ngOnInit(): void {
    this.searchAllCash();
  }

  searchAllCash() {
    this.cashOperationService.allOpeningCash().then((data) => {
      this.cashList = data;
      });
  }

  navigateToCashFlow(cashId: string) {
      this.router.navigate(['/cash-flow', cashId]);
  }
}
