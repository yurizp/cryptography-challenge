package br.com.yurizp.cryptography.domain.dto.imp;

import br.com.yurizp.cryptography.domain.dto.UserDto;

public class UserDtoImp extends UserDto {

    private UserDtoImp(String id, String userDocument, String creditCardToken, long value) {
        super(id, userDocument, creditCardToken, value);
    }

    public static UserDto create(String id, String userDocument, String creditCardToken, long value) {
        return new UserDtoImp(id, userDocument, creditCardToken, value);
    }
}
