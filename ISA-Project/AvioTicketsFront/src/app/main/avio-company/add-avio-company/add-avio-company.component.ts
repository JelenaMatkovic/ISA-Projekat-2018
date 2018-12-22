import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-add-avio-company',
  templateUrl: './add-avio-company.component.html',
  styleUrls: ['./add-avio-company.component.css']
})
export class AddAvioCompanyComponent implements OnInit {

  form:FormGroup;
  
  constructor(private formBuilder:FormBuilder) { }

  ngOnInit() {
    //this.form = this.formBuilder.group({
    //  Name:['', Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]+')])],
    //  AvioCompanyId:['',  Validators.compose([Validators.required, Validators.pattern('[a-zA-Z ]+')])],
    //});
  }

}
