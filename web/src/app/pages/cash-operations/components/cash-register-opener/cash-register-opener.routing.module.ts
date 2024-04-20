import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CashRegisterOpenerComponent } from './cash-register-opener.component';

@NgModule({
    imports: [RouterModule.forChild([
        { path: '', component: CashRegisterOpenerComponent}
    ])],
    exports: [RouterModule]
})
export class CashRegisterOpenerRouting { }
