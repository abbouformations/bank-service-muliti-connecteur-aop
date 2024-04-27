package ma.formations.multiconnector.presentation.grpc;

import io.grpc.stub.StreamObserver;
import lombok.AllArgsConstructor;
import ma.formations.multiconnector.dtos.customer.*;
import ma.formations.multiconnector.grpc.stub.Bank;
import ma.formations.multiconnector.grpc.stub.BankServiceGrpc;
import ma.formations.multiconnector.service.ICustomerService;
import net.devh.boot.grpc.server.service.GrpcService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@GrpcService
@AllArgsConstructor
public class BankGrpcController extends BankServiceGrpc.BankServiceImplBase {
    private ICustomerService customerService;
    private ModelMapper modelMapper;


    @Override
    public void customers(Bank.CustomersRequest request, StreamObserver<Bank.CustomersResponse> responseObserver) {
        List<CustomerDto> customers = customerService.getAllCustomers();
        responseObserver.onNext(Bank.CustomersResponse.newBuilder().
                addAllCustomers(customers.
                        stream().
                        map(dto -> modelMapper.map(dto, Bank.CustomerDTO.class)).
                        collect(Collectors.toList())).
                build());
        responseObserver.onCompleted();
    }

    @Override
    public void customerByIdentity(Bank.CustomerByIdentityRequest request, StreamObserver<Bank.CustomerByIdentityResponse> responseObserver) {
        String identityRef = request.getIdentityRef();
        CustomerDto dto = customerService.getCustomByIdentity(identityRef);
        Bank.CustomerByIdentityResponse response = Bank.CustomerByIdentityResponse.newBuilder().
                setCustomer(modelMapper.map(dto, Bank.CustomerDTO.class)).
                build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void createCustomer(Bank.CreateCustomerRequest request, StreamObserver<Bank.CreateCustomerResponse> responseObserver) {
        AddCustomerResponse addCustomerResponse = customerService.createCustomer(modelMapper.map(request, AddCustomerRequest.class));
        Bank.CreateCustomerResponse response = Bank.CreateCustomerResponse.newBuilder().
                setCustomer(modelMapper.map(addCustomerResponse, Bank.CustomerDTO.class)).
                setMessage(addCustomerResponse.getMessage()).
                build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void updateCustomer(Bank.UpdateCustomerRequest request, StreamObserver<Bank.UpdateCustomerResponse> responseObserver) {
        UpdateCustomerResponse updateCustomerResponse = customerService.updateCustomer(request.getIdentityRef(),
                modelMapper.map(request.getUpdatedCustomer(), UpdateCustomerRequest.class));

        Bank.UpdateCustomerResponse response = Bank.UpdateCustomerResponse.newBuilder().
                setMessage(updateCustomerResponse.getMessage()).
                setCustomer(modelMapper.map(updateCustomerResponse, Bank.CustomerDTO.class)).
                build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
