import { Address } from '../../models/address/address.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({ providedIn: 'root' })
export class AddressService {
  constructor(private httplient: HttpClient) {}

  handleError() {
    return throwError(() => {
      new Error('Something wrong');
    });
  }
  addressesServer: string = environment.gatewayService+'/clean-archi-bs-ms-address';

  getAllAddresses(): Observable<Array<Address>> {
    return this.httplient
      .get<Array<Address>>(this.addressesServer + '/addresses')
      .pipe(catchError(this.handleError));
  }

  createAddress(address: Address): Observable<Address> {
    return this.httplient
      .post<Address>(this.addressesServer + '/addresses', address)
      .pipe(catchError(this.handleError));
  }

  getAddressByInfos(
    num: number,
    street: string,
    pb: number,
    city: string,
    country: string
  ): Observable<Array<Address>> {
    return this.httplient
      .get<Array<Address>>(
        this.addressesServer +
          '/addresses?num=' +
          num +
          '&street=' +
          street +
          '&pb=' +
          pb +
          '&city=' +
          city +
          '&country=' +
          country
      )
      .pipe(catchError(this.handleError));
  }

  deleteAddress(addressID: number | any): Observable<void> {
    return this.httplient
      .delete<void>(this.addressesServer + '/addresses/' + addressID)
      .pipe(catchError(this.handleError));
  }

  getAddress(addressID: number | any): Observable<Address> {
    return this.httplient
      .get<Address>(this.addressesServer + '/addresses/' + addressID)
      .pipe(catchError(this.handleError));
  }

  updateAddress(address: Address): Observable<Address> {
    return this.httplient
      .put<Address>(
        this.addressesServer + '/addresses/' + address.addressID,
        address
      )
      .pipe(catchError(this.handleError));
  }

  getEmployeesLiveAtAddress(addressID: number|any): Observable<Array<any>> {
    return this.httplient
      .get<Array<any>>(this.addressesServer + '/employees/addresses/' + addressID)
      .pipe(catchError(this.handleError));
  }
}
