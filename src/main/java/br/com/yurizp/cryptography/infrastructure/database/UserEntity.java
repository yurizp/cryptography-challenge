package br.com.yurizp.cryptography.infrastructure.database;

import br.com.yurizp.cryptography.domain.model.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "usuario")
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "documento")
    private String userDocument;
    @Column(name = "token_cartao")
    private String creditCardToken;
    @Column(name = "valor")
    private long value;

    public static UserEntity create(User user){
        return UserEntity.builder()
                .id(user.getId())
                .userDocument(user.getUserDocument())
                .creditCardToken(user.getCreditCardToken())
                .value(user.getValue())
                .build();
    }

    public User toModel(){
        return User.builder()
                .id(this.getId())
                .userDocument(this.getUserDocument())
                .creditCardToken(this.getCreditCardToken())
                .value(this.getValue())
                .build();
    }

}
