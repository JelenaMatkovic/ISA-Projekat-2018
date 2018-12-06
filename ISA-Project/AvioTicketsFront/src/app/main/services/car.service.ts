import { Injectable } from '@angular/core';
import { SERVER_URL } from '../main.constant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http:HttpClient) { }

  getAllCarsByRentACar(rentCarId){
    return this.http.get(SERVER_URL + '/rent-a-car/' + rentCarId + '/car');
  }
  
}
