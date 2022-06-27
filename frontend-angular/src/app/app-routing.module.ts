import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AboutComponent } from './components/about/about.component';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { FormPessoaComponent } from './components/pages/pessoa/form-pessoa/form-pessoa.component';
import { HomePessoaComponent } from './components/pages/pessoa/home-pessoa/home-pessoa.component';
import { WelcomeComponent } from './components/welcome/welcome.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'home', component: WelcomeComponent },
  {
    path: 'home', component: HomeComponent,
    children: [
      { path: 'pessoa', component: HomePessoaComponent},
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
