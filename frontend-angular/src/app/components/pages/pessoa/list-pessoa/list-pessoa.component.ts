import { Component, OnInit } from '@angular/core';
import { faSearch, faTimes, faPencil, faPlus } from '@fortawesome/free-solid-svg-icons';
import { Pessoa } from 'src/interfaces/Pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';
import { MessageService } from 'src/app/services/message.service';

@Component({
  selector: 'app-list-pessoa',
  templateUrl: './list-pessoa.component.html',
  styleUrls: ['./list-pessoa.component.css']
})
export class ListPessoaComponent implements OnInit {

  faSearch = faSearch;
  faTimes = faTimes;
  faPencil = faPencil;
  faPlus = faPlus;
  
  allpessoas: Pessoa[] = [];
  constructor(private pessoaService: PessoaService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.Listpessoas();
  }

  Listpessoas() {
    this.pessoaService.getallPessoas().subscribe((itens) => {
      console.log(itens);
      this.allpessoas = itens;
    });
  }

  async remover(id: Number) {
    if (!confirm('Tem certeza que deseja deletar o item?')) {
      return;
    }

    await this.pessoaService.delete(id).subscribe();

    this.allpessoas = this.allpessoas.filter(item => item.id != id);

    this.messageService.add('Excluido com sucesso!');


  }

}
