import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../menu/menu.component';
import { PiePaginaPrincipalComponent } from '../pie-pagina-principal/pie-pagina-principal.component';
import { OrderService, Order } from '../servicio/acciones/order.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-ordenes-personalizadas',
  imports: [ MenuComponent, PiePaginaPrincipalComponent , CommonModule],
  templateUrl: './ordenes-personalizadas.component.html',
  styleUrls: ['./ordenes-personalizadas.component.css'],
  standalone: true,
})
export class OrdenesPersonalizadasComponent implements OnInit {

  ordenes: Order[] = [];

  constructor(private orderService: OrderService) { }

  ngOnInit(): void {
  this.orderService.getOrders().subscribe({
    next: (data) => {
      this.ordenes = data;
      console.log('Órdenes recibidas:', this.ordenes);
    },
    error: (err) => console.error('Error al cargar las órdenes', err)
  });
 }
}
