import { NgModule } from '@angular/core';

import { PrimengModule } from 'src/app/primeng.module';
import { CashRegisterListComponent } from './cash-register-list.component';
import { CashRegisterListRouting } from './cash-register-list.routing.module';

@NgModule({
    imports: [
        CashRegisterListRouting,
        PrimengModule
    ],
    declarations: [CashRegisterListComponent]
})
export class CashRegisterListModule { }
