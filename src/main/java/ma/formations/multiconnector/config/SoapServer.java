package ma.formations.multiconnector.config;

import lombok.AllArgsConstructor;
import ma.formations.multiconnector.presentation.soap.SoapController;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class SoapServer {

    private Bus bus;
    private SoapController soapController;

    @Bean
    public EndpointImpl bankWSEndpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, soapController);
        endpoint.publish("/BankService");
        return endpoint;
    }
}
