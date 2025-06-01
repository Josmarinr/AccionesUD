import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';

export interface Order {
  id: number;
  name: string;
  country: string;
  logo: string;
  grafica: string;
  compraPrecio: number;
  ventaPrecio: number;
  cantidad: number;
  moneda: string;
}

@Injectable({
  providedIn: 'root'
})
export class OrderService {
  private apiUrl = ''; // Cambia a la URL de tu API cuando esté disponible

  constructor(private http: HttpClient) { }

  getOrders(): Observable<Order[]> {
    // Datos de prueba mientras no se conecta al back end
    const testOrders: Order[] = [
      {
        id: 1,
        name: 'ECOPETROL S.A.',
        country: 'COL',
        logo: './ecopetrol-logo.svg',
        grafica: './grafica-ecopetrol.svg',
        compraPrecio: 1816,
        ventaPrecio: 1798,
        cantidad: 0.5,
        moneda: 'COP'
      },
      {
        id: 2,
        name: 'TESLA, INC.',
        country: 'EE.UU.',
        logo: './tesla-logo.svg',
        grafica: './grafica-tesla.svg',
        compraPrecio: 350.23,
        ventaPrecio: 342.82,
        cantidad: 0.3,
        moneda: 'USD'
      }
    ];
    return of(testOrders);
  }
}
