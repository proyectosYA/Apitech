import { Component, OnInit } from '@angular/core';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-lista-usuario',
  templateUrl: './lista-usuario.component.html',
  styleUrls: ['./lista-usuario.component.css']
})
export class ListaUsuarioComponent implements OnInit {

  usuarios: Usuario[] = [];

  isAdmin = false;
  constructor(private usuarioService:UsuarioService) { }

  ngOnInit() {
    this.obtenerProductos();
  }

  obtenerProductos(): void{
    this.usuarioService.usuarios().subscribe(
      data =>{
        this.usuarios = data;
        console.log(this.usuarios);
      },
      err =>{
        console.log(err);
      }
    );
  }

}
