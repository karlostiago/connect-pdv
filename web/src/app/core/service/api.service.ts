import {HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ErroHandlerService } from './error-handler.service';
import { environment } from 'src/environments/environment';

export abstract class ApiService<T> {

  protected baseURL = environment.apiUrl;

  protected constructor(
      protected error: ErroHandlerService) {
  }

  protected abstract pathURL(): string;

  options(httpParams: HttpParams = new HttpParams()) {
      return {
          headers: this.headers(),
          httpParams
      }
  }

  headers(): HttpHeaders {
      let headers = new HttpHeaders();
      headers = headers.append("Content-Type", "application/json");
      return headers;
  }

  toPromise<T>(request: Observable<Object>): Promise<T> {
      return new Promise((resolve) => {
          request.subscribe({
              next: (data) => {
                  resolve(data as T);
              },
              error: (error) => {
                  this.error.capturar(error);
              }
          })
      })
  }
}

export interface Page<T> {
  content: T[];
  empty: false;
  first: true;
  last: false;
  number: number;
  numberOfElements: number;
  size: number;
  sort: { sorted: false, unsorted: true, empty: true };
  totalElements: number;
  totalPages: number;
}
