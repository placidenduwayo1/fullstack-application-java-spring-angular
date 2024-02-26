import { AddressEventPublisher } from '../../../../shared/events-publisher/events.publisher';
import {  Router } from '@angular/router';
import { AddressService } from '../../../../shared/services/service-REST/addresses.service';
import { Address } from '../../../../shared/models/address/address.model';
import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { AddressEvent } from 'src/app/shared/models/events.model';

@Component({
  selector: 'app-sub-compo-address-printer',
  templateUrl: './sub-compo-address-printer.component.html',
  styleUrls: ['./sub-compo-address-printer.component.scss'],
})
export class SubCompoAddressPrinterComponent implements OnInit{
  @Input() addressesList!: Address[];
  @Output () nbrOfAddressesEmitter: EventEmitter<number> = new EventEmitter();

  constructor(
    private addressService: AddressService,
    private router: Router,
    private addressEventPublisher: AddressEventPublisher,
  ) {}


  ngOnInit(): void {
    this.addressEventPublisher.addressEventObservable.subscribe((addressEvent: AddressEvent)=>{
      switch(addressEvent) {
        case AddressEvent.EMPLOYEES_AT_ADDRESS:
          this.router.navigateByUrl('employees-at-address/'+this.addressEmployeesRelated);
          console.log(addressEvent);
          break;
      }
    })
  }

  printNbrAddressesEventEmitter(nbAddresses: number){
    this.nbrOfAddressesEmitter.emit(nbAddresses);
  }


  onAddressUpdate(address: Address) {
    this.router.navigateByUrl('address-form-update/' + address.addressID);
  }

  onAddressDelete(addressID: string) {
    if (window.confirm('CONFIRM DELETE')) {
      this.addressService
        .deleteAddress(addressID)
        .subscribe(()=>{
          this.addressEventPublisher.publishAddressEvent(AddressEvent.REFRESH);
        });
    }
  }

  addressEmployeesRelated!: number;

  onPrintEmployeesLivigOnThisAddress(addressID: number){
    this.addressEmployeesRelated = addressID;
    this.addressEventPublisher.publishAddressEvent(AddressEvent.EMPLOYEES_AT_ADDRESS);
  }
}
