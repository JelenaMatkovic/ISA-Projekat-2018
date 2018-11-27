import { HomeComponent } from "./home/home.component";
import { ProfileComponent } from "./profile/profile.component";
import { LoginComponent } from "./login/login.component";
import { RegisterComponent } from "./register/register.component";
import { AddRentACarComponent } from "./rent-a-car/add-rent-a-car/add-rent-a-car.component";


export const MainRoutes=[
    {path : '', component: HomeComponent},
    {path: 'profile', component : ProfileComponent},
    {path: 'login', component: LoginComponent},
    {path: 'register', component:RegisterComponent},
    {path: 'add-rent-a-car', component : AddRentACarComponent},
]