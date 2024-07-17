package br.com.yurizp.cryptography.infrastructure.database;

import br.com.yurizp.cryptography.domain.model.User;

public interface UserRepositoryPort {

    User findById(Long id) throws Throwable;
    User saveOrUpdate(User user);

}
