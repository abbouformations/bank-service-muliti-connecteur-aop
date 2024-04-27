package ma.formations.multiconnector.presentation.soap;


import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebResult;
import jakarta.jws.WebService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.formations.multiconnector.dtos.customer.*;
import ma.formations.multiconnector.service.ICustomerService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@WebService(serviceName = "BankWS")
@AllArgsConstructor
public class SoapController {

    private ICustomerService customerService;

    @WebMethod
    @WebResult(name = "Customers")
    public List<CustomerDto> customers() {
        return customerService.getAllCustomers();
    }

    @WebMethod
    @WebResult(name = "Customer")
    public CustomerDto customerByIdentity(@WebParam(name = "identity") String identity) {
        return customerService.getCustomByIdentity(identity);
    }


    @WebMethod
    @WebResult(name = "Customer")
    public AddCustomerResponse create(@WebParam(name = "customer") AddCustomerRequest dto) {
        return customerService.createCustomer(dto);
    }

    @WebMethod
    @WebResult(name = "Customer")
    public UpdateCustomerResponse update(@WebParam(name = "identityRef") String identityRef, @WebParam(name = "customer") UpdateCustomerRequest dto) {
        return customerService.updateCustomer(identityRef, dto);
    }

    @WebMethod
    @WebResult(name = "message")
    public String delete(@WebParam(name = "identityRef") @Valid String identityRef) {
        return customerService.deleteCustomerByIdentityRef(identityRef);
    }
}
