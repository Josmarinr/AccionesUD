import { Component, OnInit } from '@angular/core';
import { CommonModule, DatePipe } from '@angular/common';
import { Menu2Component } from '../menu2/menu2.component';
import { PiePaginaPrincipalComponent } from '../pie-pagina-principal/pie-pagina-principal.component';
import { NotificacionesService, Notificacion } from '../servicio/notificaciones/notificaciones.service';

@Component({
  selector: 'app-notificaciones',
  imports: [CommonModule, Menu2Component, PiePaginaPrincipalComponent],
  templateUrl: './notificaciones.component.html',
  styleUrl: './notificaciones.component.css',
  standalone: true,
  providers: [DatePipe]
})
export class NotificacionesComponent implements OnInit {
  notificaciones: Notificacion[] = [];
  expandedNotifications: { [key: number]: boolean } = {};

  constructor(
    private notificacionesService: NotificacionesService,
    private datePipe: DatePipe
  ) {}

  ngOnInit(): void {
    this.cargarNotificaciones();
  }

  cargarNotificaciones(): void {
    this.notificacionesService.getNotificaciones().subscribe({
      next: (data) => {
        // Ordenar por fecha (las más recientes primero)
        this.notificaciones = data.sort((a, b) =>
          new Date(b.fecha_de_notificacion).getTime() - new Date(a.fecha_de_notificacion).getTime()
        );
      },
      error: (error) => {
        console.error('Error al cargar notificaciones:', error);
      }
    });
  }

  formatFecha(fecha: Date): string {
    return this.datePipe.transform(fecha, 'dd/MM/yyyy') || '';
  }

  toggleNotificacion(index: number): void {
    const estadoActual = this.isExpanded(index);

    this.expandedNotifications[index] = !this.expandedNotifications[index];

    if (!estadoActual && !this.notificaciones[index].leida_notificacion) {
      this.marcarComoLeida(this.notificaciones[index]);
    }
  }

  isExpanded(index: number): boolean {
    return this.expandedNotifications[index] || false;
  }

  marcarComoLeida(notif: Notificacion): void {
    notif.leida_notificacion = true;
    // llamada al servicio para actualizar en el backend
  }

  filtrarNotificaciones(tipo: string = 'todas'): void {
    this.notificacionesService.getNotificaciones().subscribe({
      next: (data) => {
        if (tipo === 'todas') {
          this.notificaciones = data;
        } else {
          this.notificaciones = data.filter(n => n.tipo_de_notificacion === tipo);
        }

        // Ordenar por fecha (las más recientes primero)
        this.notificaciones = this.notificaciones.sort((a, b) =>
          new Date(b.fecha_de_notificacion).getTime() - new Date(a.fecha_de_notificacion).getTime()
        );
      },
      error: (error) => {
        console.error('Error al filtrar notificaciones:', error);
      }
    });
  }
}
