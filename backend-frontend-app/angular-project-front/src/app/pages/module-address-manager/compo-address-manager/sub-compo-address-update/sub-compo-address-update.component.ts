import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AddressService } from '../../../../shared/services/service-REST/addresses.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-sub-compo-address-update',
  templateUrl: './sub-compo-address-update.component.html',
  styleUrls: ['./sub-compo-address-update.component.scss']
})
export class SubCompoAddressUpdateComponent implements OnInit {
  addressForm!: FormGroup;

  constructor(private addressService: AddressService,
    private activatedRoute: ActivatedRoute,
    private formBuilder: FormBuilder) {}

  ngOnInit(): void {

    this.activatedRoute.data.subscribe((data)=>{
      let address = data['getAddressByIDResolve'];
      this.addressForm = this.formBuilder.group({
        addressID:[address.addressID],
        num:[address.num, [Validators.required, Validators.min(1)]],
        street: [address.street, [Validators.required, Validators.minLength(5)]],
        pb: [address.pb,[Validators.required, Validators.min(10000)]],
        city: [address.city, [Validators.required, Validators.minLength(5)]],
        country: [address.country, [Validators.required, Validators.minLength(5), Validators.maxLength(50)]]
      });
    });
  }

  onAddressFormSubmit(){
    if(window.confirm("COMFIRM ADDRESS UPDATE")){
      this.addressService.updateAddress(this.addressForm.value).subscribe(updatedAddress=>{
        console.log(updatedAddress);
      });
    }
  }

}
