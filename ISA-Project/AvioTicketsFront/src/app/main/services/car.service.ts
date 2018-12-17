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

  addCar(rentCarId, car){
    return this.http.post(SERVER_URL + '/rent-a-car/' + rentCarId + '/car', car);
  }
  
  getCarById(rentCarId,carId){
    return this.http.get(SERVER_URL+'/rent-a-car/' + rentCarId + '/car/' + carId);
  }

  updateCar(rentACarId,carId,car){
    return this.http.put(SERVER_URL + '/rent-a-car/'+rentACarId + '/car/' + carId,car);
  }

  deleteCar(rentACarId,carId){
    return this.http.delete(SERVER_URL + '/rent-a-car/'+rentACarId + '/car/'+ carId);
  }
}
