package br.com.yurizp.cryptography.controllers.rest.request;

import br.com.yurizp.cryptography.domain.dto.UserDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;

public class UserRequest extends UserDTO {

    public UserRequest(Long id, String userDocument, String creditCardToken, long value) {
        super(id, userDocument, creditCardToken, value);
    }

    @NotEmpty
    public String getUserDocument(){
        return super.getUserDocument();
    }

    @NotEmpty
    public String getCreditCardToken(){
        return super.getCreditCardToken();
    }

    @Null(message = "Not allow send Id.")
    public Long getId(){
        return super.getId();
    }
}
