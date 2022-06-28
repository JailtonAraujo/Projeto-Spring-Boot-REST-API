import { Component, OnInit } from '@angular/core';
import { Pessoa } from 'src/interfaces/Pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-new-pessoa',
  templateUrl: './new-pessoa.component.html',
  styleUrls: ['./new-pessoa.component.css']
})
export class NewPessoaComponent implements OnInit {

  btnText = "Salvar";

  constructor(private messageService:MessageService,
    private pessoaService:PessoaService) { }

  ngOnInit(): void {
  }

  async createHandler(pessoa:Pessoa){
    
    await this.pessoaService.savePessoa(pessoa).subscribe();

    this.messageService.add("Pessoa Cadastrada com sucesso!");
  }

}
