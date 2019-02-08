import { Component, OnInit, Input } from '@angular/core';
import { BranchService } from '../../services/branch.service';
import { Router } from '@angular/router';
import { MatTableDataSource } from '@angular/material';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'branch-list',
  templateUrl: './branch-list.component.html',
  styleUrls: ['./branch-list.component.css']
})
export class BranchListComponent implements OnInit {

  @Input()rentACarId:number;
  branches:any;
  displayedColumns = ['address','id'];
  dataSource:MatTableDataSource<any>;

  canAddBranch:boolean;
  canEditBranch:boolean;
  canDeleteBranch:boolean;

  constructor(private branchService:BranchService,
              private router:Router,
              private authService:AuthService) { }

  ngOnInit() {
    this.canAddBranch = this.authService.getUserType() == 'ADMIN_RENT_A_CAR';
    this.canEditBranch = this.authService.getUserType() == 'ADMIN_RENT_A_CAR';
    this.canDeleteBranch = this.authService.getUserType() == 'ADMIN_RENT_A_CAR';
    this.branchService.getAllBranchesByRentACar(this.rentACarId).subscribe(
      data =>{ 
        this.branches = data
        this.createTableElements();
      }
    );
  }

  createTableElements(){
    this.dataSource = new MatTableDataSource<any>(this.branches);
  }

  deleteBranch(branchId){
    this.branchService.deleteBranch(this.rentACarId, branchId).subscribe(()=>{
      const index =this.branches.findIndex(branch => branch.id === branchId);
      this.branches.splice(index,1);
      this.createTableElements();
    })
    
  }
}
