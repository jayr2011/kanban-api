package br.com.products.kanban.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationRequestDto {
    private String name;
    private String email;
    private Long documentNumber;
    private String password;
}
