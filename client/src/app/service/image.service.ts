import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent, HttpParams} from '@angular/common/http';
import {Meme} from '../images/images.component';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ImageService {
  SERVER = 'http://localhost:8080';
  constructor(private http: HttpClient) { }

  saveImage(file: File, description: string) {
    const formData: FormData = new FormData();
    formData.append('file', file, file.name);
    formData.append('description', description);
    return this.http.post(this.SERVER + '/createMeme', formData);
  }

  getAllMemes(): Observable<Meme[]> {
    return this.http.get<Meme[]>(this.SERVER + '/meme');
   }

  deleteMeme(id) {
    return this.http.delete(this.SERVER + `/deleteFile/${id}`);
  }

  editMeme(id, file: File, description: string) {
    const formData: FormData = new FormData();
    if (file) {
      formData.append('file', file, file.name);
    }
    formData.append('description', description);
    return this.http.put(this.SERVER + `/editMeme/${id}`, formData);
  }

  findById(id): Observable<Meme> {
    // @ts-ignore
    return this.http.get(this.SERVER + `/find/${id}`);
  }

  findByDescription(searchDescription: string) {
    const params1 = new HttpParams().set('searchDescription', searchDescription);
    return this.http.get<Meme[]>(this.SERVER + `/findByDesc`,  {params: params1});
  }

}
