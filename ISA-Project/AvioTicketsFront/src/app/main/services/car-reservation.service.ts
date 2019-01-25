import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';

@Injectable({
  providedIn: 'root'
})
export class CarReservationService {

  constructor(private http:HttpClient) {
    this.createReservation = this.createReservation.bind(this);
  }

  getReservationHistory(){
    return this.http.get(SERVER_URL + '/car-reservation');
  }

  createReservation(reservation){
    return this.http.post(SERVER_URL + '/car-reservation', reservation);
  }

  cancelReservation(reservationId){
    return this.http.delete(SERVER_URL + '/car-reservation/' + reservationId);
  }
  
}
