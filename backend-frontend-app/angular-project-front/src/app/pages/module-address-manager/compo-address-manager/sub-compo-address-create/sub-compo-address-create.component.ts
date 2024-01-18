import { Address } from '../../../../shared/models/address/address.model';
import { AddressService } from '../../../../shared/services/service-REST/addresses.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-sub-compo-address-create',
  templateUrl: './sub-compo-address-create.component.html',
  styleUrls: ['./sub-compo-address-create.component.scss']
})
export class SubCompoAddressCreateComponent implements OnInit {

  addressForm!: FormGroup;
  constructor(private formBuilder: FormBuilder,
    private addressService: AddressService) { }

  ngOnInit(): void {
    this.addressForm = this.formBuilder.group({
      addressID:['', Validators.required],
      num:['', [Validators.required, Validators.min(1)]],
      street: ['', [Validators.required, Validators.minLength(5)]],
      pb: ['',[Validators.required, Validators.min(10000)]],
      city: ['', [Validators.required, Validators.minLength(5)]],
      country: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(50)]]
    });

  }

  onAddressFormSubmit(){
    if(window.confirm("CONFIRM")){
      this.addressService.createAddress(this.addressForm.value).subscribe((newAddress: Address)=>{
        console.log(newAddress);
      })
    }

  }

}
