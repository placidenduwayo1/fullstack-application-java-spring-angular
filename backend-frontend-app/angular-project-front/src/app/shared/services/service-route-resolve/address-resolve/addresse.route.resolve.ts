import { Injectable, inject } from "@angular/core";
import { ActivatedRouteSnapshot, Resolve, ResolveFn } from "@angular/router";
import { Observable } from "rxjs";
import { Address } from "src/app/shared/models/address/address.model";
import { AddressService } from "../../service-REST/addresses.service";

export const GetAllAddressesResolve : ResolveFn<Array<Address>> = ()=>{
  return inject(AddressService).getAllAddresses();
}
export const GetAddressByIDResolve : ResolveFn<Address> = (route: ActivatedRouteSnapshot)=>{
  return inject(AddressService).getAddress(route.paramMap.get('addressID'));
}

export const GetEmployeesAtAddressResolve: ResolveFn<Array<any>> = (route: ActivatedRouteSnapshot)=>{
  return inject(AddressService).getEmployeesLiveAtAddress(route.paramMap.get('addressID'));
}
