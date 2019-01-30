import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MainComponent } from './main.component';
import { NavbarComponent } from './navbar/navbar.component';
import {MatToolbarModule, MatButtonModule, MatIconModule} from '@angular/material';
import {RouterModule} from '@angular/router';
import { HomeComponent } from './home/home.component';
import { MainRoutes } from './main.route';
import { ProfileComponent } from './profile/profile.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { SharedModule } from '../shared/shared.module';
import {FlexLayoutModule} from '@angular/flex-layout';
import { ReactiveFormsModule } from '@angular/forms';
import { AddRentACarComponent } from './rent-a-car/add-rent-a-car/add-rent-a-car.component';
import { AddCarComponent } from './car/add-car/add-car.component';
import { RentACarListComponent } from './rent-a-car/rent-a-car-list/rent-a-car-list.component';
import { RentACarProfileComponent } from './rent-a-car/rent-a-car-profile/rent-a-car-profile.component';
import { AddBranchComponent } from './branch/add-branch/add-branch.component';
import { CarListComponent } from './car/car-list/car-list.component';
import { BranchListComponent } from './branch/branch-list/branch-list.component';
import { EditRentACarComponent } from './rent-a-car/edit-rent-a-car/edit-rent-a-car.component';
import { EditCarComponent } from './car/edit-car/edit-car.component';
import { AddAvioCompanyComponent } from './avio-company/add-avio-company/add-avio-company.component';
import { EditBranchComponent } from './branch/edit-branch/edit-branch.component';
import { CarReservationComponent } from './car-reservation/car-reservation/car-reservation.component';
import { CarReservationDialogComponent } from './car-reservation/car-reservation-dialog/car-reservation-dialog.component';
import { CarQuickTicketListComponent } from './car-quick-ticket/car-quick-ticket-list/car-quick-ticket-list.component';
import { AddCarQuickTicketComponent } from './car-quick-ticket/add-car-quick-ticket/add-car-quick-ticket.component';
import { CarReservationHistoryComponent } from './car-reservation/car-reservation-history/car-reservation-history.component';
import { HomePageComponent } from './home-page/home-page.component';
import { ShowAvioCompaniesComponent } from './avio-company/show-avio-companies/show-avio-companies.component';
import { ShowAvioCompanyComponent } from './avio-company/show-avio-company/show-avio-company.component';
import { UpdateAvioCompanyComponent } from './avio-company/update-avio-company/update-avio-company.component';
import { AddDestinationComponent } from './avio-company/add-destination/add-destination.component';
import { UpdateDestinationComponent } from './avio-company/update-destination/update-destination.component';
import { RatingDialogComponent } from './rating/rating-dialog/rating-dialog.component';

@NgModule({
  declarations:[
    MainComponent, 
    NavbarComponent, 
    HomeComponent, 
    ProfileComponent, 
    LoginComponent, 
    RegisterComponent,
    AddRentACarComponent,
    AddCarComponent,
    RentACarListComponent,
    RentACarProfileComponent,
    AddBranchComponent,
    CarListComponent,
    BranchListComponent,
    EditRentACarComponent,
    EditCarComponent,
    EditBranchComponent,
    AddAvioCompanyComponent,
    CarReservationComponent,
    CarReservationDialogComponent,
    CarQuickTicketListComponent,
    AddCarQuickTicketComponent,
    CarReservationHistoryComponent,
    HomePageComponent,
    ShowAvioCompaniesComponent,
    ShowAvioCompanyComponent,
    UpdateAvioCompanyComponent,
    AddDestinationComponent,
    UpdateDestinationComponent,
    RatingDialogComponent,
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(MainRoutes),
    SharedModule,
    FlexLayoutModule,
    ReactiveFormsModule
  ],
  entryComponents:[CarReservationDialogComponent,RatingDialogComponent],
  exports: [MainComponent]
})
export class MainModule { }
