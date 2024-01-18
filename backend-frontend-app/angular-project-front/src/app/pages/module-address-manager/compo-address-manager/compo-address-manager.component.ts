import { AddressService } from '../../../shared/services/service-REST/addresses.service';
import { Address } from '../../../shared/models/address/address.model';
import { AddressEvent } from '../../../shared/models/events.model';
import { AddressEventPublisher } from '../../../shared/events-publisher/events.publisher';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-compo-address-manager',
  templateUrl: './compo-address-manager.component.html',
  styleUrls: ['./compo-address-manager.component.scss']
})
export class CompoAddressPrinterComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private addressEventPubliser : AddressEventPublisher,
    private addressService: AddressService,
    private router: Router) { }

  addressesList!: Array<Address>;

  ngOnInit(): void {
    this.addressEventPubliser.addressEventObservable.subscribe((addressEvent: AddressEvent)=>{
      switch(addressEvent){

        case AddressEvent.GET_ALL_ADDRESSES:
          this.activatedRoute.data.subscribe(addresses=>{
            this.addressesList = addresses["getAllAddressesResolve"];
            console.log(addresses)
            console.log(AddressEvent.GET_ALL_ADDRESSES)
          });
          console.log(addressEvent);
          break;

        case AddressEvent.CREATE_ADDRESS_FORM:
          console.log(addressEvent);
          this.router.navigateByUrl("address-form-create");
          break;

        case AddressEvent.REFRESH:
          this.addressService.getAllAddresses().subscribe((addresses: Array<Address>)=>{
            this.addressesList=addresses
          });
          break;
      }
    });
  }

  onPrintAddresses(){
    this.addressEventPubliser.publishAddressEvent(AddressEvent.GET_ALL_ADDRESSES)
  }

  onCreateAddress(){
    this.addressEventPubliser.publishAddressEvent(AddressEvent.CREATE_ADDRESS_FORM);
  }

}
