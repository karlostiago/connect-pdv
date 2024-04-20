import { NgModule } from '@angular/core';
import { CashRegisterOpenerComponent } from './cash-register-opener.component';
import { CashRegisterOpenerRouting } from './cash-register-opener.routing.module';
import { PrimengModule } from 'src/app/primeng.module';

@NgModule({
    imports: [
        CashRegisterOpenerRouting,
        PrimengModule
    ],
    declarations: [CashRegisterOpenerComponent]
})
export class CashRegisterOpenerModule { }
