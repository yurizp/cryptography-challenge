package br.com.yurizp.cryptography.domain.port.interfaces;

import br.com.yurizp.cryptography.domain.dto.UserDto;

public interface UserServicePort {

    UserDto getById(String id) throws Throwable;
    UserDto create(UserDto userDto) throws Throwable;

}
