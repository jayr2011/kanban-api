package br.com.products.kanban.dto.user;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserViewDto {
    private UUID id;
    private String name;
    private String email;
    private Long documentNumber;
}
