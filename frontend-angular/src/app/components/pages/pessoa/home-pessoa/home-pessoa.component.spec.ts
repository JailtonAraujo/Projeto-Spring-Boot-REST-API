import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomePessoaComponent } from './home-pessoa.component';

describe('HomePessoaComponent', () => {
  let component: HomePessoaComponent;
  let fixture: ComponentFixture<HomePessoaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomePessoaComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HomePessoaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
