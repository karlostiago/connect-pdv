import { RouterModule } from '@angular/router';
import { NgModule } from '@angular/core';
import { AppLayoutComponent } from "./layout/app.layout.component";

@NgModule({
    imports: [
        RouterModule.forRoot([
            {
                path: '', component: AppLayoutComponent,
                children: [
                    {
                        path: 'cash-operations',
                        children: [
                            {
                                path: 'cash-register-opener',
                                loadChildren: () => import('./pages/cash-operations/components/cash-register-opener/cash-register-opener.module')
                                    .then(m => m.CashRegisterOpenerModule)
                            },

                            {
                                path: 'cash-register-list',
                                loadChildren: () => import('./pages/cash-operations/components/cash-register-list/cash-register-list.module')
                                    .then(m => m.CashRegisterListModule)
                            },
                            {
                                path: 'cash-flow/:cashId',
                                loadChildren: () => import ('./pages/cash-operations/components/cash-flow/cash-flow.module')
                                    .then(m => m.CashFlowModule)
                            }

                        ]
                    }
                ]
            },
        ], { scrollPositionRestoration: 'enabled', anchorScrolling: 'enabled', onSameUrlNavigation: 'reload' })
    ],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
