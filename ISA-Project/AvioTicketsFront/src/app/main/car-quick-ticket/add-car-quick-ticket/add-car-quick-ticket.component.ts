import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { CarService } from '../../services/car.service';
import { BranchService } from '../../services/branch.service';
import { Router, ActivatedRoute } from '@angular/router';
import { CarQuickTicketService } from '../../services/car-quick-ticket.service';

@Component({
  selector: 'app-add-car-quick-ticket',
  templateUrl: './add-car-quick-ticket.component.html',
  styleUrls: ['./add-car-quick-ticket.component.css']
})
export class AddCarQuickTicketComponent implements OnInit {

  
  form:FormGroup;
  rentACarId:number;
  branches:any;
  cars:any;
  constructor(private formBuilder:FormBuilder,
              private ticketServce:CarQuickTicketService,
              private carService:CarService,
              private branchService:BranchService,
              private router:Router,
              private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params=>{
      this.rentACarId = params.rentACarId;
    })
    this.form = this.formBuilder.group({
      carId:['',Validators.required],
      placeTake:['',Validators.required],
      placeReturn:['',Validators.required],
      dateTake:['',Validators.required],
      dateReturn:['',Validators.required],
    });
    this.carService.getAllCarsByRentACar(this.rentACarId).subscribe(
      data => this.cars = data  
    );
    this.branchService.getAllBranchesByRentACar(this.rentACarId).subscribe(
      data => this.branches = data  
    );
  }
  
  saveTicket(){
    const ticket = this.form.getRawValue();
    this.ticketServce.createTicket(this.rentACarId, ticket).subscribe(data=>
        this.router.navigateByUrl('/rent-a-car/' + this.rentACarId)
    );
  }

}
