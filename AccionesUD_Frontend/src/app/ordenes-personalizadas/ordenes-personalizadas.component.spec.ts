import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OrdenesPersonalizadasComponent } from './ordenes-personalizadas.component';

describe('OrdenesPersonalizadasComponent', () => {
  let component: OrdenesPersonalizadasComponent;
  let fixture: ComponentFixture<OrdenesPersonalizadasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [OrdenesPersonalizadasComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(OrdenesPersonalizadasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
