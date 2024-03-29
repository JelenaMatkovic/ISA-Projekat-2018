import { Component, OnInit } from '@angular/core';
import { HotelService} from '../../services/hotel.service';

@Component({
  selector: 'app-hotel',
  templateUrl: './hotel.component.html',
  styleUrls: ['./hotel.component.css']
})
export class HotelComponent implements OnInit {

  hotel$: Object;

  constructor(private HotelService: HotelService) { }

  ngOnInit() {
    this.HotelService.getHotels().subscribe(
      data => this.hotel$ = data
    )
  }

}
