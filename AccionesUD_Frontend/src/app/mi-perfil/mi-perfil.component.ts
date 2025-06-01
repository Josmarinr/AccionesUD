import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { MenuComponent } from '../menu/menu.component';


@Component({
  selector: 'app-mi-perfil',
  templateUrl: './mi-perfil.component.html',
  styleUrls: ['./mi-perfil.component.css'],
  standalone: true,
  imports: [MenuComponent,
    ReactiveFormsModule,
    HttpClientModule
  ]
})
export class MiPerfilComponent {
  perfilForm!: FormGroup;

  constructor(private fb: FormBuilder, private router: Router) { }

  ngOnInit(): void {
    // Aquí simulas o traes la información del usuario, normalmente desde un servicio
    const userData = {
      firstname: 'Juan',
      lastname: 'Pérez',
      id: '1020304050',
      phone: '3001234567',
      username: 'usuario@correo.com',
      address: 'Calle 123 #45-67, Bogotá'
    };

    this.perfilForm = this.fb.group({
      firstname: [userData.firstname, Validators.required],
      lastname: [userData.lastname, Validators.required],
      id: [userData.id, Validators.required],
      phone: [userData.phone, Validators.required],
      username: [userData.username, [Validators.required, Validators.email]],
      address: [userData.address, Validators.required]
    });
  }

  onSubmit(): void {
    if (this.perfilForm.valid) {
      console.log('Perfil actualizado:', this.perfilForm.value);
    } else {
      console.log('El formulario es inválido');
    }
  }

  cancelarRegistro(): void {
  this.router.navigate(['/']);
  }
}
