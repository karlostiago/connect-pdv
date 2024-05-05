import { NgModule } from '@angular/core';
import {LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AppLayoutModule } from './layout/app.layout.module';
import { AlertService } from './core/service/alert.service';
import { ErroHandlerService } from './core/service/error-handler.service';
import { MessageService } from 'primeng/api';

@NgModule({
    declarations: [AppComponent],
    imports: [AppRoutingModule, AppLayoutModule],
    providers: [
        { provide: LocationStrategy, useClass: PathLocationStrategy },
        AlertService, ErroHandlerService, MessageService
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
