package br.com.yurizp.cryptography.domain.model;

public class UserStub {
    public static User create(){
        return User.builder()
                .id(1234L)
                .userDocument("userDocument")
                .creditCardToken("creditCardToken")
                .value(4444)
                .build();
    }

    public static User createWithoutId(){
        return User.builder()
                .userDocument("userDocument")
                .creditCardToken("creditCardToken")
                .value(4444)
                .build();

    }
}