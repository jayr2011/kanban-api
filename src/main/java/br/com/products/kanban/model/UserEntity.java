package br.com.products.kanban.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.NoArgsConstructor;

@Getter
@Setter
@Document(collection = "users")
public class UserEntity {
    private String name;
    @Id
    private String id;
    private Long documentNumber;
    @Email(message = "email should be valid")
    private String email;
    @JsonIgnore
    private String password;
}
