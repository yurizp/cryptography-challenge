package br.com.yurizp.cryptography.domain.port;

public interface EncryptionServicePort {
    String encryptSha512(String userDocument);
}
