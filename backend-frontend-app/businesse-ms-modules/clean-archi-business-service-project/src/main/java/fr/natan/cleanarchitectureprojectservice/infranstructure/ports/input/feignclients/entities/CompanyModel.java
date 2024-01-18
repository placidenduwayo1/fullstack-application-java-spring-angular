package fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities;

import fr.natan.cleanarchitectureprojectservice.infranstructure.ports.input.feignclients.entities.enums.CompanyType;

import java.time.LocalDateTime;

public class CompanyModel {
    private String companyID;
    private String companyName;
    private CompanyType companyType;
    private LocalDateTime connectedDate;
    private String agency;

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public LocalDateTime getConnectedDate() {
        return connectedDate;
    }

    public void setConnectedDate(LocalDateTime connectedDate) {
        this.connectedDate = connectedDate;
    }

    public String getAgency() {
        return agency;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return "Company:[" +
                "ID='" + companyID + '\'' +
                ", company name='" + companyName + '\'' +
                ", company type=" + companyType +
                ", connected date=" + connectedDate +
                ", agency='" + agency + '\'' +
                ']';
    }
}
