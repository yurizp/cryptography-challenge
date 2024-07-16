package br.com.yurizp.cryptography.infrastructure.database;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryIntern extends CrudRepository<UserEntity, Long> {
}
