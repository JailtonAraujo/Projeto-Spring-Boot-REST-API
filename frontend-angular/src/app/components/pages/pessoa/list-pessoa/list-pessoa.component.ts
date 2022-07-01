import { Component, OnInit } from '@angular/core';
import { faSearch, faTimes, faPencil, faPlus } from '@fortawesome/free-solid-svg-icons';
import { Pessoa } from 'src/interfaces/Pessoa';
import { PessoaService } from 'src/app/services/pessoa.service';
import { MessageService } from 'src/app/services/message.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

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
  formSearch!:FormGroup;
  nameSearch:String | null =null;

  itensPerPage:Number = 5;
  p:any;
  total=0;

  allpessoas: Pessoa[] = [];
  constructor(private pessoaService: PessoaService,
    private messageService: MessageService) { }

  ngOnInit(): void {
    this.formSearch = new FormGroup({
      nameSea : new FormControl(this.nameSearch ? this.nameSearch : "", [Validators.required])
    });
    this.Listpessoas();
  }

  Listpessoas() {
    this.pessoaService.getallPessoas().subscribe((itens) => {
      console.log(itens.content);
      this.allpessoas = itens.content;
      this.total = itens.totalElements
    });
  }

  async remover(id: Number) {
    if (!confirm('Tem certeza que deseja deletar o item?')) {
      return;
    }

    await this.pessoaService.delete(id).subscribe();

    this.allpessoas = this.allpessoas.filter(item => item.id != id);
    if(this.allpessoas.length < 1){
      if(this.total > 1){
        this.total - 1;
      }
    }
    this.messageService.add('Excluido com sucesso!');


  }

  getNameSearch(){
    return this.formSearch.get('nameSea')!;
  }

  search(){
      if(this.formSearch.invalid || this.formSearch.value == ""){
        this.pessoaService.getallPessoas().subscribe((itens) =>
          this.allpessoas = itens
        );
      }else{
      this.pessoaService.searchByName(this.formSearch.value.nameSea).subscribe((itens)=>{
        this.allpessoas = itens;
      });
    }
  }

  carregarPagina(pagina:any){
    this.pessoaService.getallPessoasPagination(pagina-1).subscribe((itens) =>{
      this.allpessoas = itens.content;
    });
  }

}
