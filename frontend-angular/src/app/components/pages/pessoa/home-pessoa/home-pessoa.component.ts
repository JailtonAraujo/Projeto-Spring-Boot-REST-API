import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/interfaces/Pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';
import { MessageService } from 'src/app/services/message.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-home-pessoa',
  templateUrl: './home-pessoa.component.html',
  styleUrls: ['./home-pessoa.component.css']
})
export class HomePessoaComponent implements OnInit {

  Allpessoas:Pessoa[] = [];

  constructor(private pessoaService:PessoaService,
  private messageService:MessageService, private router:Router) { }

  ngOnInit(): void {
    this.getAll();
  }

  async createHandler(pessoa:Pessoa){

    await this.pessoaService.savePessoa(pessoa).subscribe();
    this.messageService.add("Salvo com sucesso!");
  }

  async deletar(id:Number){
    if(!confirm('Tem certeza que deseja deletar o item?')){
      return;
    }
    
    await this.pessoaService.delete(id).subscribe();

    this.Allpessoas = this.Allpessoas.filter(item => item.id != id);

    this.messageService.add('Excluido com sucesso!');

    
  }

  getAll(){
    this.pessoaService.getallPessoas().subscribe((itens)=>{
      this.Allpessoas = itens;
    });
  }  

}
