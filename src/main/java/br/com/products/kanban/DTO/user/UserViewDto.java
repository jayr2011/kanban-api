package br.com.products.kanban.dto.user;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewDTO {
    private String id;
    private String name;
    private String email;
    private Long documentNumber;
}
