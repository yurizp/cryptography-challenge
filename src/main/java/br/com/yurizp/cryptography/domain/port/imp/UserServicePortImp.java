package br.com.yurizp.cryptography.domain.port.imp;

import br.com.yurizp.cryptography.domain.dto.UserDTO;
import br.com.yurizp.cryptography.domain.dto.imp.UserDTOImp;
import br.com.yurizp.cryptography.domain.model.User;
import br.com.yurizp.cryptography.domain.port.EncryptionServicePort;
import br.com.yurizp.cryptography.domain.port.UserServicePort;
import br.com.yurizp.cryptography.infrastructure.database.UserRepositoryPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
class UserServicePortImp implements UserServicePort {

    private UserRepositoryPort userRepository;
    private EncryptionServicePort encryptionServicePort;

    @Override
    public UserDTO findById(Long id) throws Throwable{
        return userRepository.findById(id).toDto();
    }

    @Override
    public UserDTO saveOrUpdade(UserDTO user) throws Throwable {
        String creditCardToken = encryptionServicePort.encryptSha512(user.getCreditCardToken());
        String userDocument = encryptionServicePort.encryptSha512(user.getUserDocument());

        UserDTOImp userDTOEncripted = UserDTOImp.builder()
                .value(user.getValue())
                .userDocument(userDocument)
                .creditCardToken(creditCardToken)
                .id(user.getId())
                .build();

        return userRepository.saveOrUpdate(User.create(userDTOEncripted)).toDto();
    }

}
