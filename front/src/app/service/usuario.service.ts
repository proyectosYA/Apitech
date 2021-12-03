import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Usuario } from '../models/usuario';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  
  usuarioUrl = 'http://localhost:8080/usuario/'
  constructor(private httpClient: HttpClient) { }

  public usuarios(): Observable<Usuario[]>{
    return this.httpClient.get<Usuario[]>(this.usuarioUrl  +'lista-usuarios');
  } 
  
  public obtenerUsuario(id:number): Observable<Usuario>{
    return this.httpClient.get<Usuario>(this.usuarioUrl  + `obtener-usuario/${id}`);
  } 

  public pedidoUsuario(id:number): Observable<Usuario>{
    return this.httpClient.get<Usuario>(this.usuarioUrl  + `pedidos-usuario//${id}`);
  } 

  public agregaUsuario(usuario:Usuario): Observable<Usuario>{
    return this.httpClient.post<any>(this.usuarioUrl +'add-usuario', usuario);
  }

  public actualizarUsuario(usuario:Usuario): Observable<Usuario>{
    return this.httpClient.put<any>(this.usuarioUrl +'cambiar-info-usuario', usuario);
  }
}
