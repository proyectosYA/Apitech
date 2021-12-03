export class Usuario {
    id?: number;
    nombre: string;
    apellido: string;
    cedula: number;
    tipoUsuario: string;
    correo: string;
    password: string;

  constructor(nombre:string, apellido:string, cedula:number, tipoUsuario:string, correo:string,
                password:string){
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.tipoUsuario = tipoUsuario;
        this.correo = correo;
        this.password = password;
    }
}
