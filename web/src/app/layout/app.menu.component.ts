import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
            this.model = [
                {
                    label: 'Home',
                    items: [
                        { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/'] }
                    ]
                },

                {
                    label: 'Pdv',
                    items: [
                        {
                            label: 'Operações de Caixa', icon: 'pi pi-fw pi-cart-plus',
                            items: [
                                {label: 'Abertura de Caixa', icon: 'pi pi-fw pi-lock-open', routerLink: ['/cash-operations/cash-register-list'] },
                                {label: 'Fechamento de Caixa', icon: 'pi pi-fw pi-lock', routerLink: ['/']},
                                {label: 'Lançamentos de caixa', icon: 'pi pi-fw pi-arrow-right-arrow-left', routerLink: ['/']},
                                {label: 'Suprimento de caixa', icon: 'pi pi-fw pi-plus-circle', routerLink: ['/']},
                                {label: 'Sangria de caixa', icon: 'pi pi-fw pi-money-bill', routerLink: ['/']},
                                {label: 'Transfêrencia de caixa', icon: 'pi pi-fw pi-sync', routerLink: ['/']}
                            ]
                        }
                    ]
                }
            ];
        } 
    } 