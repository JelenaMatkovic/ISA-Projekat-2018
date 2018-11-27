import { Injectable } from '@angular/core';
import { SERVER_URL } from '../main.constant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RentACarService {

  constructor(private http:HttpClient) { }

  addRentACar(rentACar){
    return this.http.post(SERVER_URL + '/rent-a-car',rentACar );
  }

}
