import { Component } from '@angular/core';
import { MenuComponent } from '../menu/menu.component';
import { PiePaginaPrincipalComponent } from '../pie-pagina-principal/pie-pagina-principal.component';
import { RelojesComponent } from "../relojes/relojes.component";
import { Menu2Component } from '../menu2/menu2.component';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-cuerpo-principal',
  imports: [MenuComponent, PiePaginaPrincipalComponent, RelojesComponent, Menu2Component, CommonModule],
  templateUrl: './cuerpo-principal.component.html',
  styleUrl: './cuerpo-principal.component.css',
  standalone: true
})
export class CuerpoPrincipalComponent {
  get usuarioAutenticado(): boolean {
    const token = localStorage.getItem('jwt');
    if (!token) return false;

    const payload = JSON.parse(atob(token.split('.')[1]));
    return Date.now() < payload.exp * 1000;
  }

}
