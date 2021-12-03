import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ListaUsuarioComponent } from './usuario/lista-usuario.component';
import { NuevoUsuarioComponent } from './usuario/nuevo-usuario.component';
import { DetalleUsuarioComponent } from './usuario/detalle-usuario.component';
import { ActualizarUsuarioComponent } from './usuario/actualizar-usuario.component';
import { ListaProductoComponent } from './producto/lista-producto.component';
import { NuevoProductoComponent } from './producto/nuevo-producto.component';
import { DetalleProductoComponent } from './producto/detalle-producto.component';
import { ActualizarProductoComponent } from './producto/actualizar-producto.component';

//importaciones externas
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';

//importaciones agregadas
import{HttpClientModule} from '@angular/common/http';
import{FormsModule} from '@angular/forms';  

@NgModule({
  declarations: [
    AppComponent,
    ListaUsuarioComponent,
    NuevoUsuarioComponent,
    DetalleUsuarioComponent,
    ActualizarUsuarioComponent,
    ListaProductoComponent,
    NuevoProductoComponent,
    DetalleProductoComponent,
    ActualizarProductoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),  
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
