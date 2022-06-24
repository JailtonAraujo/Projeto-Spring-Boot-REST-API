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

  getPessoas():Observable<Pessoa[]>{
    return this.http.get<Pessoa[]>(`${this.UrlRequestMap}/`);
  }

  savePessoa(pessoa:Pessoa):Observable<Pessoa>{
      return this.http.post<Pessoa>(`${this.UrlRequestMap}/`,pessoa);
  }

}
