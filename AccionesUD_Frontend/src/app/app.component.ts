// app.component.ts
import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { RelojesComponent } from './relojes/relojes.component'; // 👈 importa el componente

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'acciones-ud';
}
