import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';

@Injectable({
  providedIn: 'root'
})
export class RatingService {

  constructor(private http:HttpClient) { }

  rateRentACar(rentACarId,rating){
    return this.http.post(SERVER_URL + '/rent-a-car/'+rentACarId + '/rating',rating);
  }

  rateCar(carId,rating){
    return this.http.post(SERVER_URL + '/car/' + carId + '/rating',rating);
  }

  rateHotel(hotelId,rating){
    return this.http.post(SERVER_URL + '/hotel/' + hotelId + '/rating',rating);
  }

  rateRoom(roomId,rating){
    return this.http.post(SERVER_URL + '/room/' + roomId + '/rating',rating);
  }


}
