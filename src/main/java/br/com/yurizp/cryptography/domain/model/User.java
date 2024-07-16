package br.com.yurizp.cryptography.domain.model;

import br.com.yurizp.cryptography.domain.dto.UserDTO;
import br.com.yurizp.cryptography.domain.dto.imp.UserDTOImp;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class User {

    private final Long id;
    private final String userDocument;
    private final String creditCardToken;
    private final long value;

    public static User create(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .userDocument(userDTO.getUserDocument())
                .creditCardToken(userDTO.getCreditCardToken())
                .value(userDTO.getValue())
                .build();
    }

    public UserDTO toDto(){
        return UserDTOImp.builder()
                .id(this.getId())
                .userDocument(this.getUserDocument())
                .creditCardToken(this.getCreditCardToken())
                .value(this.getValue())
                .build();
    }

}
