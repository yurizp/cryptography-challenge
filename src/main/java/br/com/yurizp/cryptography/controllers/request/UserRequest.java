package br.com.yurizp.cryptography.controllers.request;

import br.com.yurizp.cryptography.domain.dto.UserDto;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;

public class UserRequest extends UserDto {

    public UserRequest(String id, String userDocument, String creditCardToken, long value) {
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
    public String getId(){
        return super.getId();
    }
}
