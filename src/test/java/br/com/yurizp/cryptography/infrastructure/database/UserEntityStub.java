package br.com.yurizp.cryptography.infrastructure.database;

public class UserEntityStub {

    public static UserEntity create(){
        return UserEntity.builder()
                .id(1234l)
                .userDocument("userDocument")
                .creditCardToken("creditCardToken")
                .value(4444)
                .build();
    }
}