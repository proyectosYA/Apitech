import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ActualizarUsuarioComponent } from './usuario/actualizar-usuario.component';
import { DetalleUsuarioComponent } from './usuario/detalle-usuario.component';
import { ListaUsuarioComponent } from './usuario/lista-usuario.component';
import { NuevoUsuarioComponent } from './usuario/nuevo-usuario.component';

const routes: Routes = [
  {path:'',component:ListaUsuarioComponent},
  {path:'nuevo',component:NuevoUsuarioComponent},
  {path:'actualizar',component:ActualizarUsuarioComponent},
  {path:'detalle/:id',component:DetalleUsuarioComponent},
  {path:'**',redirectTo:'',pathMatch:'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
