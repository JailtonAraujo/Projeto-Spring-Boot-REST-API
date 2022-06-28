import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Pessoa } from 'src/interfaces/Pessoa';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PessoaService {
  baseApiUrl = environment.baseApiUrl;
  UrlRequestMap = `${this.baseApiUrl}pessoa`;

  constructor(private http:HttpClient) { }

  getallPessoas():Observable<Pessoa[]>{
    return this.http.get<Pessoa[]>(`${this.UrlRequestMap}/`);
  }

  savePessoa(pessoa:Pessoa):Observable<Pessoa>{
      return this.http.post<Pessoa>(`${this.UrlRequestMap}/`,pessoa);
  }

  delete(id:Number){
    return this.http.delete(`${this.UrlRequestMap}/${id}`);
  }

  edit(pessoa:Pessoa):Observable<Pessoa>{
    return this.http.put<Pessoa>(`${this.UrlRequestMap}/`, pessoa);
  }

  get(id:Number):Observable<Pessoa>{
    return this.http.get<Pessoa>(`${this.UrlRequestMap}/${id}`);
  }

}
