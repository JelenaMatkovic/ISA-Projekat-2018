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

  getDestination(){
    return this.http.get(SERVER_URL + '/destination/getAll' );
  }

  getDestinationById(destinationID:any){
    return this.http.get(SERVER_URL + '/destination/getById/' + destinationID);
  }

  addDestination(destination:any){
    return this.http.post(SERVER_URL + '/destination/create',destination,{responseType: 'text'});
  }

  deleteDestination(id:number){
    return this.http.delete(SERVER_URL + '/destination/delete/' + id,{responseType: 'text'});
  }

  updateDestination(destination:any){
    return this.http.put(SERVER_URL + '/destination/update/' + destination.id, destination,{responseType: 'text'});
}
  
}
