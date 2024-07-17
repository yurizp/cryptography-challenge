package br.com.yurizp.cryptography.domain.port;

import br.com.yurizp.cryptography.domain.dto.UserDTO;

public interface UserServicePort {

    UserDTO findById(Long id) throws Throwable;
    UserDTO saveOrUpdade(UserDTO userDto) throws Throwable;

}
