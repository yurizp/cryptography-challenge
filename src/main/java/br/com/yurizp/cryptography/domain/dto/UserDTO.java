package br.com.yurizp.cryptography.domain.dto;

import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode
public abstract class UserDTO {

    private final Long id;
    private final String userDocument;
    private final String creditCardToken;
    private final long value;

    protected UserDTO(Long id, String userDocument, String creditCardToken, long value) {
        this.id = id;
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.value = value;
    }

    public String getUserDocument(){
        return userDocument;
    }

    public String getCreditCardToken(){
        return creditCardToken;
    }

    public long getValue(){
        return value;
    }

    public Long getId(){
        return id;
    }
}
