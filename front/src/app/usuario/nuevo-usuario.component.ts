import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Usuario } from '../models/usuario';
import { UsuarioService } from '../service/usuario.service';

@Component({
  selector: 'app-nuevo-usuario',
  templateUrl: './nuevo-usuario.component.html',
  styleUrls: ['./nuevo-usuario.component.css']
})
export class NuevoUsuarioComponent implements OnInit {

  
  nombre: string = '';
  apellido: string ='';
  cedula: number = 0;
  tipoUsuario: string = '';
  correo: string =''
  pass: string ='';

  constructor(private usuarioServicio: UsuarioService,
    private toastr: ToastrService,
    private router: Router) { }

  ngOnInit(): void {  
  }

  crearUsuario(): void{
      const usuario = new Usuario(this.nombre,this.apellido,this.cedula, this.tipoUsuario,
                                  this.correo,this.pass);
      this.usuarioServicio.agregaUsuario(usuario).subscribe(
         data => {
          this.toastr.success('Se creo el usuario', 'OK', {
            timeOut: 3000, positionClass: 'toast-top-center'
           
          });
          //this.router.navigate(['/']);
          console.log("exitoso")
         },
         err => {
          this.toastr.error(err.error.mensaje, 'Fail', {
            timeOut: 3000,  positionClass: 'toast-top-center'
            
          });

          console.log("exerrorrrrrr")
         }
      );
                                
  }

}
