package br.com.yurizp.cryptography.domain.port.imp;

import br.com.yurizp.cryptography.domain.port.EncryptionServicePort;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;

@Slf4j
@Service
@AllArgsConstructor
class EncryptionServicePortImp implements EncryptionServicePort {

    private final static String SAFE_WORD = "6fedc588-7aab-4adb-be5b-75fa9d1cd545";

    @Override
    @SneakyThrows
    public String encryptSha512(String input) {
        MessageDigest md = MessageDigest.getInstance("SHA-512");
        String safeInput = SAFE_WORD.concat(input);
        byte[] messageDigest = md.digest(safeInput.getBytes());
        BigInteger no = new BigInteger(1, messageDigest);
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
