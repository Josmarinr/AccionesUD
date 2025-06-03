import { Component, OnInit } from '@angular/core';
import { MenuComponent } from '../menu/menu.component';
import { ActivoComponent } from "../activo/activo.component";
import { CommonModule } from '@angular/common';

import { BehaviorSubject } from 'rxjs';

@Component({
  selector: 'app-lista-activos',
  templateUrl: './lista-activos.component.html',
  styleUrls: ['./lista-activos.component.css'],
  imports: [CommonModule, MenuComponent, ActivoComponent]
})
export class ListaActivosComponent implements OnInit {

  constructor() { }

  activos$ = new BehaviorSubject<Stock[]>([]);

  ngOnInit() {
    this.activos$.next(MOCK_STOCKS);

    setInterval(() => {
      const accionesActualizadas = this.activos$.getValue().map(accion => {
        
        // Simular nueva vela
        const last = accion.chartData[accion.chartData.length - 1];
        const nextTime = new Date(new Date(last.time).getTime() + 86400000); // siguiente día (si usamos timestamp en segundos)
        
        const open = last.close;

        // Variación aleatoria del cierre, con +/- 1% del valor
        const maxFluctuation = 0.01;
        const rand = (Math.random() * 2 - 1) * maxFluctuation; // entre -0.01 y +0.01
        const close = parseFloat((open * (1 + rand)).toFixed(2));

        // Definir un rango alrededor de open y close
        const high = parseFloat((Math.max(open, close) * (1 + Math.random() * 0.005)).toFixed(2));
        const low = parseFloat((Math.min(open, close) * (1 - Math.random() * 0.005)).toFixed(2));

        const newCandle = {
          time: nextTime.toISOString().split('T')[0],
          open: open,
          high: high,
          low: low,
          close: close,
        };

        return {
          ...accion,
          sellPrice: open,
          buyPrice: close,
          chartData: [...accion.chartData, newCandle],
        };

      });

      this.activos$.next(accionesActualizadas);
    }, 5000);

    

  }

}


export const MOCK_STOCKS = [
  {
    name: 'ECOPETROL S.A.',
    buyPrice: 1.816,
    sellPrice: 1.798,
    country: 'COL',
    chartData: [
      { time: '2024-05-01', open: 2200, high: 2300, low: 2100, close: 2250 },
      { time: '2024-05-02', open: 2250, high: 2350, low: 2200, close: 2300 },
      // más datos simulados...
    ]
  },
  {
    name: 'TESLA, INC.',
    buyPrice: 350.23,
    sellPrice: 342.82,
    country: 'EE.UU.',
    chartData: [
      { time: '2024-05-01', open: 2500, high: 2550, low: 2450, close: 2520 },
      { time: '2024-05-02', open: 2520, high: 2580, low: 2500, close: 2560 },
      // más datos simulados...
    ]
  }
];

export interface Stock {
  name: string;
  buyPrice: number;
  sellPrice: number;
  country: string;
  chartData: {
    time: string;
    open: number;
    high: number;
    low: number;
    close: number;
  }[];
}