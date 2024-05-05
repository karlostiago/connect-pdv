import { NgModule } from '@angular/core';
import { PrimengModule } from 'src/app/primeng.module';
import { CashFlowComponent } from './cash-flow.component';


@NgModule({
    imports: [
        PrimengModule
    ],
    declarations: [CashFlowComponent]
})
export class CashFlowModule { }
