import { environment } from 'src/environments/environment';
import { Observable, throwError, catchError } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Company } from '../../models/company/company.model';

@Injectable({ providedIn: 'root' })
export class CompanyService {
  constructor(private httpClient: HttpClient) {}

  dbServer = environment.gatewayService+'/clean-archi-bs-ms-company';

  handleError() {
    return throwError(() => {
      new Error('Something wrong');
    });
  }

  getAllCompanies(): Observable<Array<Company>> {
    return this.httpClient
      .get<Array<Company>>(this.dbServer + '/companies')
      .pipe(catchError(this.handleError));
  }

  createCompany(company: Company): Observable<Company> {
    return this.httpClient
      .post<Company>(this.dbServer + '/companies', company)
      .pipe(catchError(this.handleError));
  }

  getCompany(companyID: string | any): Observable<Company> {
    return this.httpClient
      .get<Company>(this.dbServer + '/companies/' + companyID)
      .pipe(catchError(this.handleError));
  }

  updateCompany(company: Company): Observable<Company> {
    return this.httpClient
      .put<Company>(this.dbServer + '/companies/' + company.companyID, company)
      .pipe(catchError(this.handleError));
  }

  deleteCompany(companyID: string): Observable<void> {
    return this.httpClient
      .delete<void>(this.dbServer + '/companies/' + companyID)
      .pipe(catchError(this.handleError));
  }

  getProjectsAssignedToCompany(
    companyID: string | any
  ): Observable<Array<any>> {
    return this.httpClient
      .get<Array<any>>(this.dbServer + '/projects/companies/' + companyID)
      .pipe(catchError(this.handleError));
  }
}
