package br.com.yurizp.cryptography.domain.port.imp;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@ExtendWith(MockitoExtension.class)
class EncryptionServicePortImpTest {

    @InjectMocks
    private EncryptionServicePortImp encryptionServicePortImp;

    @Test
    void shouldEncrypt() {
        String encryptedSha512 = "ee26b0dd4af7e749aa1a8ee3c10ae9923f618980772e473f8819a5d4940e0db27ac185f8a0e1d5f84f88bc887fd67b143732c304cc5fa9ad8e6f57f50028a8ff";
        String encryptedSha512WithSafeWord = "ae99696c1971ff310812bd0796e2263314c083af09f4d22b0edc9419b082d4517b2a4229f6d1b003ef69b329a80dbcf49431f420656cb5ff871c987f23a4b98f";

        String encrypted = encryptionServicePortImp.encryptSha512("test");
        assertAll(
                () -> assertEquals(encryptedSha512WithSafeWord, encrypted),
                () -> assertNotEquals(encryptedSha512, encrypted)
        );
    }
}