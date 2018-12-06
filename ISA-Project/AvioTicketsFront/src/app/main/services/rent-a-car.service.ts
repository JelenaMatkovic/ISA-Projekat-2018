import { Injectable } from '@angular/core';
import { SERVER_URL } from '../main.constant';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class RentACarService {

  constructor(private http:HttpClient, private authService:AuthService) { }

  addRentACar(rentACar){
    return this.http.post(SERVER_URL + '/rent-a-car',rentACar );
  }

  getAllRentACars(){
    return this.http.get(SERVER_URL + '/rent-a-car');
  }

  getRentACarById(rentACarId){
    return this.http.get(SERVER_URL + '/rent-a-car/'+rentACarId);
  }

  updateRentACar(rentACarId, rentACar){
    return this.http.put(SERVER_URL + '/rent-a-car/'+rentACarId, rentACar);
  }

  deleteRentACar(rentACarId){
    return this.http.delete(SERVER_URL + '/rent-a-car/'+rentACarId)
  }

}
