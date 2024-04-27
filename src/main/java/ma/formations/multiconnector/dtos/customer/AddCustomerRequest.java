package ma.formations.multiconnector.dtos.customer;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AddCustomerRequest {

    @NotEmpty(message = "username must not be empty")
    private String username;
    @NotEmpty(message = "identityRef must not be empty")
    private String identityRef;
    @NotEmpty(message = "firstname must not be empty")
    private String firstname;
    @NotEmpty(message = "lastname must not be empty")
    private String lastname;
}
