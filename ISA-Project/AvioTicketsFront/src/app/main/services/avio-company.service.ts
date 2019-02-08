import { Injectable } from '@angular/core';
import { SERVER_URL } from '../main.constant';
import { HttpClient } from '@angular/common/http';
import { AuthService } from './auth.service';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AvioCompanyService {

  constructor(private http:HttpClient, 
              private authService:AuthService) { }

  getAvioCompanies(){
    return this.http.get(SERVER_URL + '/aviocompany/getAll' );
  }

  getAvioCompanyById(avioCompany:any){
    return this.http.get(SERVER_URL + '/aviocompany/getById/' + avioCompany);
  }

  updateAvioCompany(avioCompany:any){

      return this.http.put(SERVER_URL + '/aviocompany/update/' + avioCompany.id, avioCompany,{responseType: 'text'});
  }

  getDestination(avioCompanyId:any){
    return this.http.get(SERVER_URL + '/destination/getAll/' + avioCompanyId );
  }

  getDestinationById(destinationID:any){
    return this.http.get(SERVER_URL + '/destination/getById/' + destinationID);
  }

  addDestination(avioCompanyId:any,destination:any){
    return this.http.post(SERVER_URL + '/destination/create/' + avioCompanyId ,destination,{responseType: 'text'});
  }

  deleteDestination(idAvio:number,idDest:number){
    return this.http.delete(SERVER_URL + '/destination/delete/' + idAvio + '/' + idDest,{responseType: 'text'});
  }

  updateDestination(avioCompanyId:any,destination:any){
    return this.http.put(SERVER_URL + '/destination/update/'+ avioCompanyId + '/' + destination.id, destination,{responseType: 'text'});
}

  getFlights(avioCompanyId:any){
    return this.http.get(SERVER_URL + '/flight/getAll/' + avioCompanyId );
  }

  getFlightById(flight:any){
    return this.http.get(SERVER_URL + '/flight/getById/' + flight);
  }

  addFlight(avioId:number,flight:any){
    return this.http.post(SERVER_URL + '/flight/create/' + avioId,flight,{responseType: 'text'});
  }

  searchFlights(search:any){
    return this.http.post(SERVER_URL + '/search/searchFlights',search);
  }
  
  addReservation(idFlight:number,users:any){
    return this.http.post(SERVER_URL + '/ticket/create/' + idFlight,users,{responseType: 'text'});
  }

  getAllReservationOfUser(){
    return this.http.get(SERVER_URL + '/ticket/getAll/')
  }

  removeReservation(id:any){
    return this.http.delete(SERVER_URL + '/ticket/delete/' + id);
  }

}
