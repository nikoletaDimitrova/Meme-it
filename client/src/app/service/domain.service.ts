import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import {Domains} from '../domain/domain.component';
import {Meme} from '../images/images.component';

@Injectable({
  providedIn: 'root'
})
export class DomainService {
  url = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getAllDomains() {
    return this.http.get<Domains[]>(this.url + '/allDomains');
  }

  getMemes(domain) {
    const params1 = new HttpParams().set('domain', domain);
    return this.http.get<Meme[]>(this.url + '/otherMemes', {params: params1});
  }

  searchDescription(searchDescription) {
    return this.http.get<Meme[]>(this.url + `/search/${searchDescription}`);
  }
}
