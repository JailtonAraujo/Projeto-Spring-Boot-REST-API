import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EditPessoaComponent } from './components/pages/pessoa/edit-pessoa/edit-pessoa.component';
import { ListPessoaComponent } from './components/pages/pessoa/list-pessoa/list-pessoa.component';
import { NewPessoaComponent } from './components/pages/pessoa/new-pessoa/new-pessoa.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: WelcomeComponent },
  { path: 'home', component: HomeComponent,
    children: [
      { path: 'pessoa', component: ListPessoaComponent},
      { path: 'new', component:NewPessoaComponent},
      { path: 'edit/:id', component:EditPessoaComponent}
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
