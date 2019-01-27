import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { SERVER_URL } from '../main.constant';

@Injectable({
  providedIn: 'root'
})
export class CarQuickTicketService {

  constructor(private http:HttpClient) { 
    this.reserveTicket = this.reserveTicket.bind(this);
  }

  getTicketsByRentACar(rentCarId){
    return this.http.get(SERVER_URL+'/rent-a-car/' + rentCarId + '/car-ticket');
  }

  createTicket(rentCarId,ticket){
    ticket.dateTake = ticket.dateTake.format("YYYY-MM-DDTHH:mm:ss");
    ticket.dateReturn = ticket.dateReturn.format("YYYY-MM-DDTHH:mm:ss");
    return this.http.post(SERVER_URL+'/rent-a-car/' + rentCarId + '/car-ticket',ticket);
  }

  getTickets(){
    return this.http.get(SERVER_URL+'/car-ticket');
  }

  reserveTicket(ticketId){
    return this.http.post(SERVER_URL + '/car-ticket/' + ticketId + '/reservation', {});
  }


}
