package br.com.yurizp.cryptography.infrastructure.database.imp;


import br.com.yurizp.cryptography.domain.error.imp.BadRequestError;
import br.com.yurizp.cryptography.domain.model.User;
import br.com.yurizp.cryptography.infrastructure.database.UserEntity;
import br.com.yurizp.cryptography.infrastructure.database.UserEntityStub;
import br.com.yurizp.cryptography.infrastructure.database.UserRepositoryIntern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserRepositoryPortImplTest {

    @InjectMocks
    private UserRepositoryPortImpl userRepositoryPort;
    @Mock
    private UserRepositoryIntern userRepositoryIntern;


    @Test
    void shouldReturnErrorWhenNotFindUserById() {
        when(userRepositoryIntern.findById(anyLong())).thenReturn(Optional.empty());

        BadRequestError badRequestError = assertThrows(BadRequestError.class, () -> userRepositoryPort.findById(123L));

        assertAll(
                () -> assertEquals(badRequestError.getSimpleError().code(), "user.notfound"),
                () -> assertEquals(badRequestError.getSimpleError().message(), "User not found")
        );
    }

    @Test
    void shouldReturnUserById() throws Throwable {
        UserEntity user = UserEntityStub.create();

        when(userRepositoryIntern.findById(anyLong())).thenReturn(Optional.of(user));

        User userResponse = userRepositoryPort.findById(123L);
        assertThat(user.toModel(), samePropertyValuesAs(userResponse));
    }

}