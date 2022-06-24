import { Component, Input, OnInit } from '@angular/core';
import { FormGroup , FormControl, Validators} from '@angular/forms';
import { Usuario } from 'src/interfaces/Usuario';
import { LoginService } from 'src/app/services/login.service';
import { MessageService } from 'src/app/services/message.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userForm!:FormGroup;
  @Input() usuario:Usuario | null = null;

  constructor(private loginService:LoginService,
    private messageService:MessageService,
    private router:Router) { }

  ngOnInit(): void {
    this.userForm = new FormGroup({
      login : new FormControl (this.usuario ? this.usuario.login : "", [Validators.required]),
      senha : new FormControl (this.usuario ? this.usuario.senha : "", [Validators.required])
    })
  }

  get login(){
    return this.userForm.get('login')!;
  }

  get senha (){
    return this.userForm.get('senha')!;
  }

  async submit(){
    if(this.userForm.invalid){
      return;
    }
    await this.loginService.logar(this.userForm.value).subscribe((Response)=>{

      let token = JSON.parse(JSON.stringify(Response)).Authorization;
      console.log(token);
      localStorage.setItem("token",token);
      this.messageService.add("Bem vindo ao sistema!");
      this.router.navigate(["home"]);

    },
   error =>{
      console.log(error);
      this.messageService.add("Erros de autenticação: Nome de usuario ou senha incorretos");
    }
    );

   
  }

}
