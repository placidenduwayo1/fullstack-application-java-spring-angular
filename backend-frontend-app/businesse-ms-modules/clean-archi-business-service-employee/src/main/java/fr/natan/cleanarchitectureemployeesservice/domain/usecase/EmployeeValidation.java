package fr.natan.cleanarchitectureemployeesservice.domain.usecase;

import fr.natan.cleanarchitectureemployeesservice.domain.exception_metrier.ExceptionWarnMsg;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.input.feignclient.models.AddressModel;
import fr.natan.cleanarchitectureemployeesservice.infrastructure.ports.output.model.EmployeeDto;

@SuppressWarnings("ALL")
public class EmployeeValidation {
    public static boolean areValidEmployeeRequiredFields(EmployeeDto employeeDto) {
        if (employeeDto.getFirstname().isBlank()
                || employeeDto.getLastname().isBlank()
                || employeeDto.getAddressID()<=0
        ){
            return false;
        }

        return true;
    }

    public static void employeeFormatter(EmployeeDto employeeDto){
        employeeDto.setFirstname(employeeDto.getFirstname().strip());
        employeeDto.setLastname(employeeDto.getLastname().strip());
    }
    public static String buildEmployeeProfessionalEmail(String firstname, String lastname, String domain){
        lastname = lastname.strip();
        firstname = firstname.strip();
         lastname = lastname.replaceAll("\\s","-").toLowerCase();
        firstname = firstname.replaceAll("\\s","-").toLowerCase();

        return firstname+"."+lastname+"@"+domain+".fr";
    }

    public static boolean isValidAddressAPI(AddressModel addressModel){
        if(addressModel.getAddressID().equals(ExceptionWarnMsg.ADDRESS_API_ERROR.toString())){
            return false;
        }
        return true;
    }
}
