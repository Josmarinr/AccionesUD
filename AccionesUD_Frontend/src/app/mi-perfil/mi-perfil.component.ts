import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  Validators,
  ReactiveFormsModule,
  FormArray,
} from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { Router } from '@angular/router';
import { MenuComponent } from '../menu/menu.component';
import {
  UserProfileService,
  UpdateUserProfileRequest,
} from '../servicio/gestionusuario/userProfileService';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-mi-perfil',
  templateUrl: './mi-perfil.component.html',
  styleUrls: ['./mi-perfil.component.css'],
  standalone: true,
  imports: [MenuComponent, ReactiveFormsModule, HttpClientModule, CommonModule],
})
export class MiPerfilComponent implements OnInit {
  perfilForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private router: Router,
    private userProfileService: UserProfileService
  ) {}

  ngOnInit(): void {
    // Crear formulario vacío inicialmente
    this.perfilForm = this.fb.group({
      firstname: ['', Validators.required],
      lastname: ['', Validators.required],
      id: [{ value: '', disabled: true }],
      phone: ['', Validators.required],
      username: [{ value: '', disabled: true }],
      address: ['', Validators.required],
      otpEnabled: [false],
      dailyOrderLimit: [1],
      emails: this.fb.array([]),


    });

    this.addEmail(); 
    
    // Cargar datos reales del backend
    this.userProfileService.getMyProfile().subscribe({
      next: (data: UpdateUserProfileRequest) => {
        this.perfilForm.patchValue({
          firstname: data.firstname,
          lastname: data.lastname,
          id: data.id,
          phone: data.phone,
          username: data.username,
          address: data.address,
          otpEnabled: data.otpEnabled,
          dailyOrderLimit: data.dailyOrderLimit,
        });
      },
      error: (err) => {
        console.error('Error al cargar perfil:', err);
      },
    });
  }

  onSubmit(): void {
    if (this.perfilForm.valid) {
      const updatedProfile: UpdateUserProfileRequest = this.perfilForm.getRawValue();

      this.userProfileService.updateUserProfile(updatedProfile).subscribe({
        next: (res) => {
          console.log('Perfil actualizado exitosamente:', res);
          alert('Perfil actualizado exitosamente');
          this.router.navigate(['/']);
        },
        error: (err) => {
          console.error(' Error al actualizar perfil:', err);
          alert('Ocurrió un error al actualizar el perfil');
        },
      });
    } else {
      console.log(' El formulario es inválido');
    }
  }

  cancelarRegistro(): void {
    this.router.navigate(['/']);
  }

get emails(): FormArray {
  return this.perfilForm.get('emails') as FormArray;
}

addEmail(): void {
  if (this.emails.length < 2) {
    this.emails.push(this.fb.group({ value: [''] }));
  }
}

removeEmail(index: number): void {
  this.emails.removeAt(index);
}


}
