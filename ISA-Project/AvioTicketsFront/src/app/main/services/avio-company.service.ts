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


  
}
