package br.com.yurizp.cryptography.domain.dto.imp;

import br.com.yurizp.cryptography.domain.dto.UserDTO;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public class UserDTOImp extends UserDTO {

    private UserDTOImp(Long id, String userDocument, String creditCardToken, long value) {
        super(id, userDocument, creditCardToken, value);
    }

}
