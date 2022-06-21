import { Component, Input, OnInit } from '@angular/core';
import { FormGroup , FormControl, Validators} from '@angular/forms';
import { Usuario } from 'src/interfaces/Usuario';
import { LoginService } from 'src/app/services/login.service';
import { MessageService } from 'src/app/services/message.service';
import { environment } from 'src/environments/environment';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userForm!:FormGroup;
  @Input() usuario:Usuario | null = null;

  constructor(private loginService:LoginService,
    private messageService:MessageService) { }

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
    await this.loginService.logar(this.userForm.value).subscribe((data)=>{
      let token = JSON.parse(JSON.stringify(data)).Authorization;
      localStorage.setItem("token", token);
      console.log(token);
    });

    this.messageService.add('Logado com sucesso!');
  }

}
