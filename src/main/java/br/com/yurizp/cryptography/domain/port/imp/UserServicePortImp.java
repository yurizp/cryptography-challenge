package br.com.yurizp.cryptography.domain.port.imp;

import br.com.yurizp.cryptography.domain.dto.UserDto;
import br.com.yurizp.cryptography.domain.port.UserServicePort;
import org.springframework.stereotype.Component;

@Component
public class UserServicePortImp implements UserServicePort {

    @Override
    public UserDto getById(String id) throws Throwable{
        return null;
    }

    @Override
    public UserDto create(UserDto userDto) throws Throwable {
        return userDto;
    }

}
