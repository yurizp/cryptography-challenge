package br.com.yurizp.cryptography.domain.port.imp;

import br.com.yurizp.cryptography.domain.dto.UserDTO;
import br.com.yurizp.cryptography.domain.dto.imp.UserDTOStub;
import br.com.yurizp.cryptography.domain.model.User;
import br.com.yurizp.cryptography.domain.model.UserStub;
import br.com.yurizp.cryptography.domain.port.EncryptionServicePort;
import br.com.yurizp.cryptography.infrastructure.database.UserRepositoryPort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServicePortImpTest {

    @Mock
    private UserRepositoryPort userRepositoryPort;
    @Mock
    private EncryptionServicePort encryptionServicePort;
    @InjectMocks
    private UserServicePortImp userServicePort;

    @Test
    void shouldReturnUserById() throws Throwable {
        User user = UserStub.create();
        when(userRepositoryPort.findById(anyLong())).thenReturn(user);

        UserDTO userResponse = userServicePort.findById(123L);
        assertThat(user.toDto(), samePropertyValuesAs(userResponse));
    }

    @Test
    void shouldCreateOrUpdateUser() throws Throwable {
        UserDTO userDTOInput = UserDTOStub.create();
        String creditCardEncripted = UUID.randomUUID().toString();
        String userDocumentEncripted = UUID.randomUUID().toString();

        when(encryptionServicePort.encryptSha512(eq(userDTOInput.getCreditCardToken()))).thenReturn(creditCardEncripted);
        when(encryptionServicePort.encryptSha512(eq(userDTOInput.getUserDocument()))).thenReturn(userDocumentEncripted);
        when(userRepositoryPort.saveOrUpdate(any())).thenReturn(UserStub.create());

        UserDTO userDTOResult = userServicePort.saveOrUpdade(userDTOInput);

        ArgumentCaptor<User> acInteger = ArgumentCaptor.forClass(User.class);
        verify(userRepositoryPort).saveOrUpdate(acInteger.capture());
        verify(encryptionServicePort, times(2)).encryptSha512(anyString());
        User userCaptor = acInteger.getValue();

        assertAll(
                () -> assertEquals(creditCardEncripted, userCaptor.getCreditCardToken()),
                () -> assertEquals(userDocumentEncripted, userCaptor.getUserDocument()),
                () -> assertEquals(userDTOInput.getId(), userCaptor.getId()),
                () -> assertEquals(userDTOInput.getValue(), userCaptor.getValue()),
                () -> assertThat(userDTOInput, samePropertyValuesAs(userDTOResult))
        );
    }

    @Test
    void shouldThrowErrorInCreateOrUpdateUserWhenRepositoryReturnError() {

        when(encryptionServicePort.encryptSha512(anyString())).thenReturn(UUID.randomUUID().toString());
        when(userRepositoryPort.saveOrUpdate(any())).thenThrow(new RuntimeException());

        assertThrows(RuntimeException.class, () -> userServicePort.saveOrUpdade(UserDTOStub.create()));

        verify(encryptionServicePort, times(2)).encryptSha512(anyString());
    }
}