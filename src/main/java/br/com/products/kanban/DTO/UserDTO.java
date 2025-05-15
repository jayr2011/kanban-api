package br.com.products.kanban.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO {
    private String id;
    private String name;
    private String email;
    private Long documentNumber;
    private String password;
}
