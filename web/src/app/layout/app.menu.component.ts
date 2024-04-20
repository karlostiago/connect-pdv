import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { LayoutService } from './service/app.layout.service';
import { environment } from 'src/environments/environment';

@Component({
    selector: 'app-menu',
    templateUrl: './app.menu.component.html'
})
export class AppMenuComponent implements OnInit {

    model: any[] = [];

    constructor(public layoutService: LayoutService) { }

    ngOnInit() {
        /**
        * Responsible for differentiating the routes
        * of the example components when the production profile
        * is active, the part of the menu with the examples will be disregarded,
        * only active when the production profile is false.
        */
        if (environment.production) {
            this.model = [
                {
                    label: 'Home',
                    items: [
                        { label: 'Dashboard', icon: 'pi pi-fw pi-home', routerLink: ['/'] }
                    ]
                },
            ];
        } else {
            this.model = [
                {
                    label: 'Utils',
                    items: [
                        {
                            label: 'Components', icon: 'pi pi-fw pi-bookmark',
                            items: [
                                { label: 'Form Layout', icon: 'pi pi-fw pi-id-card', routerLink: ['/uikit/formlayout'] },
                                { label: 'Input', icon: 'pi pi-fw pi-check-square', routerLink: ['/uikit/input'] },
                                { label: 'Float Label', icon: 'pi pi-fw pi-bookmark', routerLink: ['/uikit/floatlabel'] },
                                { label: 'Invalid State', icon: 'pi pi-fw pi-exclamation-circle', routerLink: ['/uikit/invalidstate'] },
                                { label: 'Button', icon: 'pi pi-fw pi-box', routerLink: ['/uikit/button'] },
                                { label: 'Table', icon: 'pi pi-fw pi-table', routerLink: ['/uikit/table'] },
                                { label: 'List', icon: 'pi pi-fw pi-list', routerLink: ['/uikit/list'] },
                                { label: 'Tree', icon: 'pi pi-fw pi-share-alt', routerLink: ['/uikit/tree'] },
                                { label: 'Panel', icon: 'pi pi-fw pi-tablet', routerLink: ['/uikit/panel'] },
                                { label: 'Overlay', icon: 'pi pi-fw pi-clone', routerLink: ['/uikit/overlay'] },
                                { label: 'Media', icon: 'pi pi-fw pi-image', routerLink: ['/uikit/media'] },
                                { label: 'Menu', icon: 'pi pi-fw pi-bars', routerLink: ['/uikit/menu'], routerLinkActiveOptions: { paths: 'subset', queryParams: 'ignored', matrixParams: 'ignored', fragment: 'ignored' } },
                                { label: 'Message', icon: 'pi pi-fw pi-comment', routerLink: ['/uikit/message'] },
                                { label: 'File', icon: 'pi pi-fw pi-file', routerLink: ['/uikit/file'] },
                                { label: 'Chart', icon: 'pi pi-fw pi-chart-bar', routerLink: ['/uikit/charts'] },
                                { label: 'Misc', icon: 'pi pi-fw pi-circle', routerLink: ['/uikit/misc'] }
                            ]
                        },
                        {
                            label: 'Icons', icon: 'pi pi-fw pi-bookmark',
                            items: [
                                { label: 'PrimeIcons', icon: 'pi pi-fw pi-prime', routerLink: ['/utilities/icons'] }
                            ]
                        },

                        {
                            label: 'Docs', icon: 'pi pi-fw pi-bookmark',
                            items: [
                                {
                                    label: 'Documentation', icon: 'pi pi-fw pi-question', routerLink: ['/documentation']
                                },
                                {
                                    label: 'View Source', icon: 'pi pi-fw pi-search', url: ['https://github.com/primefaces/sakai-ng'], target: '_blank'
                                }
                            ]
                        },
                        {
                            label: 'Pages', icon: 'pi pi-fw pi-bookmark',
                            items: [
                                {
                                    label: 'Landing',
                                    icon: 'pi pi-fw pi-globe',
                                    routerLink: ['/landing']
                                },
                                {
                                    label: 'Auth',
                                    icon: 'pi pi-fw pi-user',
                                    items: [
                                        {
                                            label: 'Login',
                                            icon: 'pi pi-fw pi-sign-in',
                                            routerLink: ['/auth/login']
                                        },
                                        {
                                            label: 'Error',
                                            icon: 'pi pi-fw pi-times-circle',
                                            routerLink: ['/auth/error']
                                        },
                                        {
                                            label: 'Access Denied',
                                            icon: 'pi pi-fw pi-lock',
                                            routerLink: ['/auth/access']
                                        }
                                    ]
                                },
                                {
                                    label: 'Crud',
                                    icon: 'pi pi-fw pi-pencil',
                                    routerLink: ['/pages/crud']
                                },
                                {
                                    label: 'Timeline',
                                    icon: 'pi pi-fw pi-calendar',
                                    routerLink: ['/pages/timeline']
                                },
                                {
                                    label: 'Not Found',
                                    icon: 'pi pi-fw pi-exclamation-circle',
                                    routerLink: ['/notfound']
                                },
                                {
                                    label: 'Empty',
                                    icon: 'pi pi-fw pi-circle-off',
                                    routerLink: ['/pages/empty']
                                },
                            ]
                        }
                    ]
                }
            ];
        }
    }
}
