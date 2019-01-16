import { Injectable } from '@angular/core';
import { SERVER_URL } from '../main.constant';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarService {

  constructor(private http:HttpClient) { }

  getAllCarsByRentACar(rentCarId,filter?){
    const queryParams:any ={};
    if(filter){
      Object.assign(queryParams, filter);
      if(filter.priceStart ) queryParams.name = filter.name;
      if(filter.priceEnd ) queryParams.location = filter.location;
    } 
    return this.http.get(SERVER_URL + '/rent-a-car/' + rentCarId + '/car', {params : queryParams});
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
