package br.com.products.kanban.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.products.kanban.converter.DocumentNumberConverter;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Column(name = "name")
    private String name;
    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "id")
    private UUID id;
    @Column(name = "documentNumber")
    @Convert(converter = DocumentNumberConverter.class)
    private Long documentNumber;
    @Column(name = "email")
    @Email(message = "email should be valid")
    private String email;
    @Column(nullable = false)
    @JsonIgnore
    private String password;
}
