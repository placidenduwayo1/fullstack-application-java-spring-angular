import { CompanyType } from "./company.type";

export class Company {
  companyID!: string;
  companyName!: string;
  agency!: string;
  companyType!: CompanyType;
  connectedDate!: Date;
}
