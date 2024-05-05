import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { Resource } from '../model/resource.model';

export abstract class ApiService<T extends Resource> {

  protected abstract readonly PATH: string;

  constructor(protected httpClient: HttpClient) { }

  post(resource: T, endpoint = ''): Observable<any> {
    return this.httpClient.post(`${this.PATH}/${endpoint}`, resource);
}

  put(resource: T, endpoint = ''): Observable<any> {
    return this.httpClient.put(`${this.PATH}/${endpoint}`, resource);
  }

  patch(resource: any, endpoint = ''): Observable<any> {

    return this.httpClient.patch(`${this.PATH}/${endpoint}`, resource);
  }

  getPage(endpoint = '', parameters = {}): Observable<any> {
    const url = `${this.PATH}/${endpoint}`;
    return this.httpClient.get<Page<T>>(url, { params: parameters });
  }

  get(endpoint = '', parameters = {}): Observable<any> {
    return this.getPage(endpoint, parameters).pipe(
      map(res => {
        return res && res.content ? res.content : res;
      })
    );
  }

  getById(id = ''): Observable<T> {
    const url = `${this.PATH}/${id}`;
    return this.httpClient.get<T>(url);
  }

  delete(id: any, parameters = {}, endpoint = ''): Observable<any> {
    console.log('Delete:' + id);

    let url = `${this.PATH}`;

    if (endpoint) {
      url += `/${endpoint}`;
    }

    url += `/${id}`;

    return this.httpClient.delete(url, parameters);
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
