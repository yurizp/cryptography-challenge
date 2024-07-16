package br.com.yurizp.cryptography.domain.dto;

public abstract class UserDto {

    private final String id;
    private final String userDocument;
    private final String creditCardToken;
    private final long value;

    protected UserDto(String id, String userDocument, String creditCardToken, long value) {
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

    public String getId(){
        return id;
    }
}
