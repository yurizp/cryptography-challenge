package br.com.yurizp.cryptography.domain.dto.imp;

import br.com.yurizp.cryptography.domain.dto.UserDTO;

public class UserDTOStub {

    public static UserDTO create(){
        return UserDTOImp.builder()
                .id(1234L)
                .userDocument("userDocument")
                .creditCardToken("creditCardToken")
                .value(4444)
                .build();
    }

    public static UserDTO createWithoutId(){
        return UserDTOImp.builder()
                .userDocument("userDocument")
                .creditCardToken("creditCardToken")
                .value(4444)
                .build();

    }
}