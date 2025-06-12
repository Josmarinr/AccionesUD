// import { Notificacion } from './notificaciones.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { of } from 'rxjs';

export interface Notificacion {
  tipo_de_notificacion: string;
  titulo_de_notificacion: string;
  texto_de_notificacion: string;
  fecha_de_notificacion: Date;
  leida_notificacion: boolean;
}

@Injectable({
  providedIn: 'root'
})
export class NotificacionesService {
  private baseUrl = ''; // URL de tu API

  constructor(private http: HttpClient) { }

  getNotificaciones(): Observable<Notificacion[]> {

    const dummyNotificaciones: Notificacion[] = [
      {
      tipo_de_notificacion: 'info',
      titulo_de_notificacion: 'Notificación de prueba 1',
      texto_de_notificacion: 'Este es el primer mensaje de prueba. Aquí se ofrece una descripción muy detallada de la notificación, incluyendo ejemplos y notas adicionales para una comprensión completa. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer nec odio. Praesent libero. Sed cursus ante dapibus diam. Sed nisi. Nulla quis sem at nibh elementum imperdiet. Duis sagittis ipsum.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
      {
      tipo_de_notificacion: 'warning',
      titulo_de_notificacion: 'Notificación de prueba 2',
      texto_de_notificacion: 'Este es el segundo mensaje de prueba. Se incluye una narrativa extensa para contextualizar el aviso, explicando posibles impactos y medidas a tomar. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas sed diam eget risus varius blandit sit amet non magna. Etiam porta sem malesuada magna mollis euismod.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: true
      },
      {
      tipo_de_notificacion: 'error',
      titulo_de_notificacion: 'Notificación de prueba 3',
      texto_de_notificacion: 'Este es el tercer mensaje de prueba. Se ha añadido un texto extenso y detallado que describe el error, sus posibles causas y soluciones. In hac habitasse platea dictumst. Curabitur blandit tempus porttitor. Vestibulum id ligula porta felis euismod semper. Donec ullamcorper nulla non metus auctor fringilla.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
      {
      tipo_de_notificacion: 'success',
      titulo_de_notificacion: 'Notificación exitosa',
      texto_de_notificacion: 'La operación se completó con éxito. La notificación ahora contiene una descripción amplia de las acciones realizadas, mejoras implementadas, y beneficios detallados para el usuario. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus sagittis lacus vel augue laoreet rutrum faucibus dolor auctor.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
      {
      tipo_de_notificacion: 'info',
      titulo_de_notificacion: 'Recordatorio',
      texto_de_notificacion: 'No olvides revisar las últimas actualizaciones. Este mensaje incluye explicaciones extensas sobre la importancia de estar al día, con ejemplos y recomendaciones para optimizar el flujo de trabajo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Cras mattis consectetur purus sit amet fermentum.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
      {
      tipo_de_notificacion: 'warning',
      titulo_de_notificacion: 'Atención',
      texto_de_notificacion: 'Existen cambios pendientes que requieren revisión. La notificación cuenta ahora con un texto detallado que abarca antecedentes, posibles problemas y pasos sugeridos para resolver cualquier discrepancia. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque velit nisi, pretium ut lacinia in, elementum id enim.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
      {
      tipo_de_notificacion: 'error',
      titulo_de_notificacion: 'Fallo en el sistema',
      texto_de_notificacion: 'Se presentó un error inesperado en la aplicación. Esta notificación se ha ampliado para incluir descripciones técnicas, análisis de la situación y recomendaciones prácticas para diagnosticar y solucionar el fallo. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Pellentesque in ipsum id orci porta dapibus.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
      {
      tipo_de_notificacion: 'info',
      titulo_de_notificacion: 'Nueva función',
      texto_de_notificacion: 'Una nueva característica ha sido añadida recientemente. Ahora se proporciona una explicación larga y detallada de la funcionalidad, sus ventajas, y ejemplos de cómo utilizarla en el día a día. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
      {
      tipo_de_notificacion: 'info',
      titulo_de_notificacion: 'Nueva función2',
      texto_de_notificacion: 'Una nueva característica ha sido añadida recientemente. Ahora se proporciona una explicación larga y detallada de la funcionalidad, sus ventajas, y ejemplos de cómo utilizarla en el día a día. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur arcu erat, accumsan id imperdiet et, porttitor at sem.',
      fecha_de_notificacion: new Date(),
      leida_notificacion: false
      },
    ];
    return of(dummyNotificaciones);
    //return this.http.get<Notificacion[]>(this.baseUrl);
  }
}
