import { Component, HostListener, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotificacionesService, Notificacion } from '../servicio/notificaciones/notificaciones.service';
import { DatePipe } from '@angular/common';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-menu2',
  standalone: true,
  imports: [CommonModule, HttpClientModule],
  templateUrl: './menu2.component.html',
  styleUrl: './menu2.component.css',
  providers: [DatePipe]
})
export class Menu2Component implements OnInit {
  showDropdown = false;
  showNotifications = false;
  notificaciones: Notificacion[] = [];

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
        ).slice(0, 5); // Tomar solo las 5 primeras
      },
      error: (error) => {
        console.error('Error al cargar notificaciones:', error);
      }
    });
  }

  toggleDropdown(event: Event): void {
    event.preventDefault();
    event.stopPropagation();
    this.showDropdown = !this.showDropdown;
    if (this.showNotifications) {
      this.showNotifications = false;
    }
  }

  toggleNotifications(event: Event): void {
    event.preventDefault();
    event.stopPropagation();
    this.showNotifications = !this.showNotifications;
    if (this.showDropdown) {
      this.showDropdown = false;
    }
  }

  formatFecha(fecha: Date): string {
    return this.datePipe.transform(fecha, 'dd/MM/yyyy') || '';
  }

  marcarTodasComoLeidas(): void {
    this.notificaciones.forEach(notif => {
      notif.leida_notificacion = true;
    });
  }

  // Cerrar el dropdown al hacer clic fuera
  @HostListener('document:click', ['$event'])
  onDocumentClick(event: MouseEvent): void {
    const menuBtn = document.querySelector('.menu-btn');
    const dropdown = document.querySelector('.user-dropdown');

    if (menuBtn && dropdown &&
        !menuBtn.contains(event.target as Node) &&
        !dropdown.contains(event.target as Node)) {
      this.showDropdown = false;
    }

    const notificationBtn = document.querySelector('.notification-btn');
    const notificationDropdown = document.querySelector('.notification-dropdown');

    if (notificationBtn && notificationDropdown &&
        !notificationBtn.contains(event.target as Node) &&
        !notificationDropdown.contains(event.target as Node)) {
      this.showNotifications = false;
    }
  }
}
