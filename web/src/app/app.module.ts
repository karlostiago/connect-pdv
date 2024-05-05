import { NgModule } from '@angular/core';
import {LocationStrategy, PathLocationStrategy } from '@angular/common';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';
import { AppLayoutModule } from './layout/app.layout.module';
import { AlertService } from './core/service/alert.service';
import { ErroHandlerService } from './core/service/error-handler.service';
import { MessageService } from 'primeng/api';
import { PrimengModule } from './primeng.module';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './auth.interceptor';
import { LoaderModule } from './core/loader/loader.module';

@NgModule({
    declarations: [AppComponent],
    imports: [AppRoutingModule, AppLayoutModule, PrimengModule, LoaderModule],
    providers: [
        { provide: LocationStrategy, useClass: PathLocationStrategy },
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
        AlertService, ErroHandlerService, MessageService,
    ],
    bootstrap: [AppComponent],
})
export class AppModule {}
