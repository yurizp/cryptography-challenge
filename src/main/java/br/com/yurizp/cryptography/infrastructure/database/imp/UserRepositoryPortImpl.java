package br.com.yurizp.cryptography.infrastructure.database.imp;

import br.com.yurizp.cryptography.domain.error.ErrorBuilder;
import br.com.yurizp.cryptography.domain.error.imp.BadRequestError;
import br.com.yurizp.cryptography.domain.model.User;
import br.com.yurizp.cryptography.infrastructure.database.UserEntity;
import br.com.yurizp.cryptography.infrastructure.database.UserRepositoryIntern;
import br.com.yurizp.cryptography.infrastructure.database.UserRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserRepositoryPortImpl implements UserRepositoryPort {

    private UserRepositoryIntern userRepositoryIntern;

    @Override
    public User findById(Long id) throws Throwable {
        return userRepositoryIntern.findById(id)
                .orElseThrow(() ->  ErrorBuilder.builder()
                        .code("user.notfound")
                        .message("User not found")
                        .build(BadRequestError.class)
                ).toModel();
    }

    @Override
    public User saveOrUpdate(User user) {
        return userRepositoryIntern.save(UserEntity.create(user)).toModel();
    }
}
