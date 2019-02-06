import { Component, OnInit} from '@angular/core';
import { AvioCompanyService } from '../../services/avio-company.service';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder } from '@angular/forms';
import { pipe } from "rxjs";
import { mapTo, delay } from 'rxjs/operators';

@Component({
  selector: 'app-search-flights',
  templateUrl: './search-flights.component.html',
  styleUrls: ['./search-flights.component.css']
})
export class SearchFlightsComponent implements OnInit {
  searchForm:FormGroup;
  filterForm:FormGroup;
  forSearch:any;
  forFilter:any;
  submitted: any;
  searchedFlights:any;
  tempA:any;
  tempSave:any;
  temp:any;
  minPrice:any;
  maxPrice:any;

  constructor(private _avioComapnyService : AvioCompanyService,
    private router:Router,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder) { }

  ngOnInit() {

    this.searchForm = this.formBuilder.group({
      typeOfPath:[null],
      numberOfAdults:[null],
      numberOfChilds: [null],
      clas:[null],
      starting_pointTown:[null],
      starting_pointCountry: [null],
      destinationTown:[null],
      destinationCountry:[null],
      dateAndTimeStart:[null],
      dateAndTimeEnd:[null]
    });
    this.filterForm = this.formBuilder.group({
      nameOfAvioCompany:[null],
      startPrice:[null],
      endPrice:[null],
      durationOfFlightsStart: [null],
      durationOfFlightsEnd: [null],
    });
    
  }

  onSubmit(event:any) {
    this.submitted = true;
    this.forSearch = this.searchForm.getRawValue();
    console.log(this.forSearch);

    this._avioComapnyService.searchFlights(this.forSearch).pipe(delay(300)).subscribe(
      data => {
          this.searchedFlights = data;
          this.tempSave = this.searchedFlights
          for (let index = 0; index < this.searchedFlights.length; index++) {
            this.temp = Date.parse(this.searchedFlights[index].dateAndTimeEnd) 
                    - Date.parse(this.searchedFlights[index].dateAndTimeStart);
            this.searchedFlights[index].timeOfTravel = Math.round(this.temp / 1000 / 60);
          }
    });
  }

  filter(event:any) {
    
    this.forFilter = this.filterForm.getRawValue();
    this.searchedFlights = this.tempSave
    console.log(this.forFilter);

    this.tempA = new Array(this.searchedFlights.length) ;
    console.log(this.tempA);

    for (let index = 0; index < this.searchedFlights.length; index++) {
        
        

        if(this.forFilter.nameOfAvioCompany != null
             && !this.forFilter.nameOfAvioCompany.includes(this.searchedFlights[index].nameOfAvioCompany)){
              if( this.forFilter.nameOfAvioCompany != "")
                      continue;
        }
        if(this.forFilter.durationOfFlightsStart != null
              && !(this.forFilter.durationOfFlightsStart <  this.searchedFlights[index].timeOfTravel)){
                if(this.forFilter.durationOfFlightsStart != "")
                continue;
        }
        if(this.forFilter.durationOfFlightsEnd != null
              && !(this.forFilter.durationOfFlightsEnd >  this.searchedFlights[index].timeOfTravel)){
                if(this.forFilter.durationOfFlightsEnd != "")
                continue;
        }
        
        //nacim min
        if(this.searchedFlights[index].ecconomic != null){
            if(this.searchedFlights[index].business != null){
                if(this.searchedFlights[index].first != null){
                  this.minPrice = Math.min( this.searchedFlights[index].ecconomic.price,
                                            this.searchedFlights[index].business.price,
                                            this.searchedFlights[index].first.price);
                }else{
                  this.minPrice = Math.min( this.searchedFlights[index].ecconomic.price,
                                            this.searchedFlights[index].business.price);
                }
            }else{
                if(this.searchedFlights[index].first != null){
                  this.minPrice = Math.min( this.searchedFlights[index].ecconomic.price,
                                            this.searchedFlights[index].first.price);
                }else{
                  this.minPrice = this.searchedFlights[index].ecconomic.price;
                }
            }
        }else{
            if(this.searchedFlights[index].business != null){
                if(this.searchedFlights[index].first != null){
                    this.minPrice = Math.min( this.searchedFlights[index].business.price,
                                              this.searchedFlights[index].first.price);
                }else{
                  this.minPrice = this.searchedFlights[index].business.price;
                }
            }else{
                if(this.searchedFlights[index].first != null){
                  this.minPrice = this.searchedFlights[index].first.price;
                }
            }
        }
        //provera min
        if(this.forFilter.startPrice != null
          && !(this.forFilter.startPrice < this.minPrice)){
            if(this.forFilter.startPrice != "")
              continue;
        }

        //nacim max
        if(this.searchedFlights[index].ecconomic != null){
          if(this.searchedFlights[index].business != null){
              if(this.searchedFlights[index].first != null){
                this.maxPrice = Math.max( this.searchedFlights[index].ecconomic.price,
                                          this.searchedFlights[index].business.price,
                                          this.searchedFlights[index].first.price);
              }else{
                this.maxPrice = Math.max( this.searchedFlights[index].ecconomic.price,
                                          this.searchedFlights[index].business.price);
              }
          }else{
              if(this.searchedFlights[index].first != null){
                this.maxPrice = Math.max( this.searchedFlights[index].ecconomic.price,
                                          this.searchedFlights[index].first.price);
              }else{
                this.maxPrice = this.searchedFlights[index].ecconomic.price;
              }
          }
      }else{
          if(this.searchedFlights[index].business != null){
              if(this.searchedFlights[index].first != null){
                  this.maxPrice = Math.max( this.searchedFlights[index].business.price,
                                            this.searchedFlights[index].first.price);
              }else{
                  this.maxPrice = this.searchedFlights[index].business.price;
              }
          }else{
              if(this.searchedFlights[index].first != null){
                  this.maxPrice = this.searchedFlights[index].first.price;
              }
          }
      }
      //provera
      if(this.forFilter.endPrice != null
        && !(this.forFilter.endPrice > this.maxPrice)){
          if(this.forFilter.endPrice != "")
          continue;
      }

        this.tempA[index] = this.searchedFlights[index];
    }
    
    

    this.searchedFlights  = this.tempA;

  }

}
