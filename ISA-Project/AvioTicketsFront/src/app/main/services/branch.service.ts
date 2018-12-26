import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';

@Injectable({
  providedIn: 'root'
})
export class BranchService {

  constructor(private http:HttpClient) { }

  getAllBranchesByRentACar(rentCarId){
    return this.http.get(SERVER_URL + '/rent-a-car/' + rentCarId + '/branch');
  }

  addBranch(rentCarId,branch){
    return this.http.post(SERVER_URL + '/rent-a-car/' + rentCarId + '/branch',branch);
  }
  
  getBranchById(rentCarId,branchId){
    return this.http.get(SERVER_URL+'/rent-a-car/' + rentCarId + '/branch/' + branchId);
  }

  updateBranch(rentACarId,branchId,branch){
    return this.http.put(SERVER_URL + '/rent-a-car/'+rentACarId + '/branch/' + branchId,branch);
  }

  deleteBranch(rentACarId,branchId){
    return this.http.delete(SERVER_URL + '/rent-a-car/'+rentACarId + '/branch/'+ branchId);
  }
}
