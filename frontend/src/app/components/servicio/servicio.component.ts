import { Component, OnInit } from '@angular/core';
import { Servicio } from 'src/app/model/servicio';

@Component({
  selector: 'app-servicio',
  templateUrl: './servicio.component.html',
  styleUrls: ['./servicio.component.css']
})
export class ServicioComponent implements OnInit {

  servicio1 : Servicio = {
    id : 1,
    modalidad: "guia de turismo",
    region: "Lima",
    plataforma: "zoom",
    usuario: "jack",
    name: "guia de macchu picchu",
    description: "visita guiada a la ciudadela ",
    init_date: "1-06-2021",
    end_date: "1-07-2021",
    price: 200 
  };

  constructor() { }

  ngOnInit(): void {
  }

}
