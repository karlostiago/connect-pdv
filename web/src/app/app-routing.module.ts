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
                                path: 'cash-register-withdrawal',
                                loadChildren: () => import('./pages/cash-operations/components/cash-register-opener/cash-register-opener.module')
                                    .then(m => m.CashRegisterOpenerModule)
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
