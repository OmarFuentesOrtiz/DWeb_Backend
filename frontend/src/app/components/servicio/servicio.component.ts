import { Component, OnInit } from '@angular/core';
import { SERVICIOS } from 'src/app/model/mock-servicios';
import { Servicio } from 'src/app/model/servicio';

@Component({
  selector: 'app-servicio',
  templateUrl: './servicio.component.html',
  styleUrls: ['./servicio.component.css']
})
export class ServicioComponent implements OnInit {

  servicios : Servicio[] = SERVICIOS;

  constructor() { }

  ngOnInit(): void {
  }

}
