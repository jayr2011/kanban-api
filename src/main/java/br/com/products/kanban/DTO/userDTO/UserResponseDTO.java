package br.com.products.kanban.DTO.userDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    private String name;
    private String email;
    private Long documentNumber;
    private String password;
}
