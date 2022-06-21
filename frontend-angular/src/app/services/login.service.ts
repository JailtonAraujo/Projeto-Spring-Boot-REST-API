import { Injectable } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import { Usuario } from 'src/interfaces/Usuario';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  baseApiUrl = environment.baseApiUrl;
  ApiUriLogar = `${this.baseApiUrl}login`;

  constructor(private http:HttpClient) { }

  logar(usuario:Usuario):Observable<Usuario>{
    return this.http.post<Usuario>(this.ApiUriLogar, JSON.stringify(usuario));
  }
}
