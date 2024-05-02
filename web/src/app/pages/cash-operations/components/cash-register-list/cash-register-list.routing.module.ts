import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CashRegisterListComponent } from './cash-register-list.component';

@NgModule({
    imports: [RouterModule.forChild([
        { path: '', component: CashRegisterListComponent}
    ])],
    exports: [RouterModule]
})
export class CashRegisterListRouting { }
