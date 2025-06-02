import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-activo',
  templateUrl: './activo.component.html',
  styleUrls: ['./activo.component.css'],
})
export class ActivoComponent implements OnInit {

  @Input() nombreActivo: string = 'AAAPL';
  @Input() precioCompra: number = 1865.5;
  @Input() precioVenta: number = 1870.5;
  @Input() pais: string = 'USA';
  @Input() chartData!: any[];

  volume: number = 0.5;

  ngOnInit() {
    
  }

}
