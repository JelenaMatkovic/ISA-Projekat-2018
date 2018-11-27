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

@NgModule({
  declarations: [
    MainComponent, 
    NavbarComponent, 
    HomeComponent, 
    ProfileComponent, 
    LoginComponent, 
    RegisterComponent,
    AddRentACarComponent,
    AddCarComponent
  ],
  imports: [
    CommonModule,
    RouterModule.forRoot(MainRoutes),
    SharedModule,
    FlexLayoutModule,
    ReactiveFormsModule
  ],
  exports: [MainComponent]
})
export class MainModule { }
