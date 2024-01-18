import { Injectable } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve } from "@angular/router";
import { Observable } from "rxjs";
import { Address } from "src/app/shared/models/address/address.model";
import { AddressService } from "../../service-REST/addresses.service";

@Injectable({ providedIn: 'root' })
export class GetAllAddressesResolve implements Resolve<Array<Address>> {
  constructor(private addressService: AddressService) {}
  resolve(): Observable<Address[]> {
    return this.addressService.getAllAddresses();
  }
}
@Injectable({providedIn:'root'})
export class GetAddressByIDResolve implements Resolve<Address> {
  constructor(private addressService: AddressService) {}
  resolve(route: ActivatedRouteSnapshot): Observable<Address> {
   return this.addressService.getAddress(route.paramMap.get('addressID'))
  }
}
@Injectable({providedIn:'root'})
export class GetEmployeesAtAddressResolve implements Resolve<Array<any>> {
  constructor (private addressService: AddressService){}
  resolve(route: ActivatedRouteSnapshot) : Observable<Array<any>>{
    let addressID: number|any = route.paramMap.get('addressID');
    return this.addressService.getEmployeesLiveAtAddress(addressID);
  }
}
