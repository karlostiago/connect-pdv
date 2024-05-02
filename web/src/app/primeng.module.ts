import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ProgressSpinnerModule } from 'primeng/progressspinner';
import { InputMaskModule } from 'primeng/inputmask';
import { ButtonModule } from 'primeng/button';
import { TabViewModule } from 'primeng/tabview';
import { HttpClientModule } from '@angular/common/http';
import { InputTextModule } from 'primeng/inputtext';
import { SidebarModule } from 'primeng/sidebar';
import { BadgeModule } from 'primeng/badge';
import { RadioButtonModule } from 'primeng/radiobutton';
import { InputSwitchModule } from 'primeng/inputswitch';
import { RippleModule } from 'primeng/ripple';
import { AppConfigModule } from './layout/config/config.module';
import { RouterModule } from '@angular/router';
import { BreadcrumbModule } from 'primeng/breadcrumb';
import { InputNumberModule } from 'primeng/inputnumber';
import { PanelModule } from 'primeng/panel';
import { MessagesModule } from 'primeng/messages';
import { DropdownModule } from 'primeng/dropdown';
import { TableModule } from 'primeng/table';
import { TagModule } from 'primeng/tag';

@NgModule({
    imports: [
        CommonModule,
        TagModule,
        FormsModule,
        ProgressSpinnerModule,
        InputMaskModule,
        ButtonModule,
        TabViewModule,
        FormsModule,
        HttpClientModule,
        InputTextModule,
        SidebarModule,
        BadgeModule,
        RadioButtonModule,
        InputSwitchModule,
        RippleModule,
        RouterModule,
        AppConfigModule,
        InputMaskModule,
        BreadcrumbModule,
        InputNumberModule,
        PanelModule,
        MessagesModule,
        DropdownModule,
        TableModule
    ],
    exports: [
        CommonModule,
        FormsModule,
        ProgressSpinnerModule,
        InputMaskModule,
        ButtonModule,
        TabViewModule,
        FormsModule,
        HttpClientModule,
        InputTextModule,
        SidebarModule,
        BadgeModule,
        RadioButtonModule,
        InputSwitchModule,
        RippleModule,
        RouterModule,
        AppConfigModule,
        InputMaskModule,
        BreadcrumbModule,
        InputNumberModule,
        PanelModule,
        MessagesModule,
        DropdownModule,
        TableModule,
        TagModule
    ]
})
export class PrimengModule { }