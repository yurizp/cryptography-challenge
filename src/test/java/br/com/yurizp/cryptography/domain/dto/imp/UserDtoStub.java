package br.com.yurizp.cryptography.domain.dto.imp;

import br.com.yurizp.cryptography.domain.dto.UserDto;

public class UserDtoStub {

    public static UserDto create(){
        return UserDtoImp.create("1234", "userDocument", "creditCardToken", 4444);
    }

    public static UserDto createWithoutId(){
        return UserDtoImp.create(null, "userDocument", "creditCardToken", 4444);
    }

}