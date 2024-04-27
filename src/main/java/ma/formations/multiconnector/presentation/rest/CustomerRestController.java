package ma.formations.multiconnector.presentation.rest;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.formations.multiconnector.aop.ExecuitionTime;
import ma.formations.multiconnector.aop.Tracabilite;
import ma.formations.multiconnector.dtos.customer.*;
import ma.formations.multiconnector.service.ICustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rest")
@AllArgsConstructor
public class CustomerRestController {

    private ICustomerService customerService;

    @ExecuitionTime
    @GetMapping("/customers")
    ResponseEntity<List<CustomerDto>> customers() {
        return new ResponseEntity<>(customerService.getAllCustomers(), HttpStatus.OK);
    }

    @ExecuitionTime
    @GetMapping("/customers/{identityRef}")
    ResponseEntity<CustomerDto> customerByIdentity(@PathVariable(name = "identityRef") String identity) {
        return new ResponseEntity<>(customerService.getCustomByIdentity(identity), HttpStatus.OK);
    }

    @ExecuitionTime
    @Tracabilite
    @PostMapping("/customers/create")
    public ResponseEntity<AddCustomerResponse> createCustomer(@Valid @RequestBody AddCustomerRequest dto) {
        return new ResponseEntity<>(customerService.createCustomer(dto), HttpStatus.CREATED);
    }

    @ExecuitionTime
    @Tracabilite
    @PutMapping("/customers/update/{identityRef}")
    public ResponseEntity<UpdateCustomerResponse> updateCustomer(@PathVariable(name = "identityRef") String identityRef, @RequestBody UpdateCustomerRequest dto) {
        return new ResponseEntity<>(customerService.updateCustomer(identityRef, dto), HttpStatus.OK);
    }

    @ExecuitionTime
    @Tracabilite
    @DeleteMapping("/customers/delete/{identityRef}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("identityRef") String identityRef) {
        return new ResponseEntity<>(customerService.deleteCustomerByIdentityRef(identityRef), HttpStatus.OK);
    }
}
