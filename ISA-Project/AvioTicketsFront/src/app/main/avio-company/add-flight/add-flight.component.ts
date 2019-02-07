import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { BranchService } from '../../services/branch.service';
import { CarService } from '../../services/car.service';
import { AvioCompanyService } from '../../services/avio-company.service';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';

@Component({
  selector: 'app-add-flight',
  templateUrl: './add-flight.component.html',
  styleUrls: ['./add-flight.component.css']
})
export class AddFlightComponent implements OnInit {

  addFlightForm:FormGroup;
  flight : any;
  avioCompanyId : any; 
  submitted = false;
  ekoCheck = false;
  bysCheck = false;
  firCheck = false;
  omoguciCheck = false;
  
  temp : any;
  tempA : any[];

  dropdownList : any;
  selectedItems = [];
  dropdownSettings = {};
  dropdownListUsluge = [];
  selectedItemsUsluge = [];
  dropdownSettingsUsluge = {};
  selectedItemsPolaziste = [];
  dropdownSettingsPolaziste = {};
  selectedItemsOdrediste = [];
  dropdownSettingsOdrediste = {};

  constructor(private _avioComapnyService : AvioCompanyService,
              private router:Router,
              private activatedRoute: ActivatedRoute,
              private formBuilder: FormBuilder) { }


  ngOnInit() {
    this.addFlightForm = this.formBuilder.group({
      id:[null],
      pathCode:[null],
      numberOfSegments: [null],
      starting_point_id:[null],
      destination_id:[null],
      dateAndTimeStart:[null],
      dateAndTimeEnd: [null],
      lengthOfTravel:[null],
      destinationOfTransfer:[null],
      typeOfPath:[null],
      additionalServices:[null],
      dateAndTimeStartReturn:[null],
      dateAndTimeEndReturn:[null],
      ecconomic:this.formBuilder.group({
        numberOfSeats:[null],
        price:[null]
      }),
      business:this.formBuilder.group({
        numberOfSeats:[null],
        price:[null]
      }),
      first:this.formBuilder.group({
        numberOfSeats:[null],
        price:[null]
      }),
      luggage:this.formBuilder.group({
        maxQuantity : [null],
        maxWeight : [null],
        maxDimensions : [null]
      })
    });

  
    this.activatedRoute.paramMap.pipe(delay(350)).subscribe(
      params => {
          this.avioCompanyId = params.get('id');
          this._avioComapnyService.getDestination(this.avioCompanyId).subscribe(
            data => this.dropdownList = data
          );
      }
    );

    this.dropdownListUsluge = [
      { item_id: 1, item_text: 'Hrana' },
      { item_id: 2, item_text: 'Pice' },
      { item_id: 3, item_text: 'Novine' }
    ];

    this.selectedItems = [];
    this.selectedItemsUsluge = [];
    this.selectedItemsPolaziste = [];
    this.selectedItemsOdrediste = [];
    
    this.dropdownSettings = {
      singleSelection: false,
      idField: 'id',
      textField: 'nameOfAirPort',
      searchPlaceholderText: 'Presedanja',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      maxHeight: 200,
      itemsShowLimit: 2,
      allowSearchFilter: true
    };

    this.dropdownSettingsUsluge = {
      singleSelection: false,
      idField: 'item_id',
      textField: 'item_text',
      searchPlaceholderText: 'Dodatne usluge',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      maxHeight: 200,
      itemsShowLimit: 2,
      allowSearchFilter: true
    };

    this.dropdownSettingsPolaziste = {
      singleSelection: true,
      idField: 'id',
      textField: 'nameOfAirPort',
      searchPlaceholderText: 'Polaziste',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      maxHeight: 200,
      itemsShowLimit: 1,
      allowSearchFilter: true
    };

    this.dropdownSettingsOdrediste = {
      singleSelection: true,
      idField: 'id',
      textField: 'nameOfAirPort',
      searchPlaceholderText: 'Odrediste',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      maxHeight: 200,
      itemsShowLimit: 1,
      allowSearchFilter: true
    };

    
    
  }

  onItemSelect(item: any) {
    console.log(item);
  }
  onSelectAll(items: any) {
    console.log(items);
  }

  get f() { return this.addFlightForm.controls; }

  onSubmit(event:any) {
    this.submitted = true;
    this.flight = this.addFlightForm.getRawValue()
    //starting_point_id
    this.dropdownList.forEach(element => {
        if(element.id == this.flight.starting_point_id[0].id){
          this.temp = element
        }
    });
    this.flight.starting_point_id = this.temp;
    //destination_id
    this.dropdownList.forEach(element => {
      if(element.id == this.flight.destination_id[0].id){
        this.temp = element
      }
    });
    this.flight.destination_id = this.temp;
    //destinationOfTransfer
    this.tempA = new Array(this.flight.destinationOfTransfer.length) ;
    for (let index = 0; index < this.flight.destinationOfTransfer.length; index++) {
      this.dropdownList.forEach(element => {
        if(element.id == this.flight.destinationOfTransfer[index].id){
            this.tempA[index] = element;
        }
      });
    }
    this.flight.destinationOfTransfer = this.tempA;

    this.flight.id = null;

    console.log(this.flight);

    this._avioComapnyService.addFlight(this.avioCompanyId,this.flight).pipe(delay(300)).subscribe(
        data => {
            this.router.navigateByUrl("avio-company/showAvioCompany/" + this.avioCompanyId)
    });

  }

  eco(event) {
    if ( event.target.checked ) {
        this.ekoCheck = true;
    }else{
        this.ekoCheck = false;
    }
  }

  bys(event) {
    if ( event.target.checked ) {
        this.bysCheck = true;
    }else{
        this.bysCheck = false;
    }
  }

  fir(event) {
    if ( event.target.checked ) {
        this.firCheck = true;
    }else{
        this.firCheck = false;
    }
  }

  omoguci(event){
    console.log(event.target.value)
    if ( event.target.value == 1 ) {
      this.omoguciCheck = true;
    }else{
      this.omoguciCheck = false;
    }
  }
}
