package br.com.products.kanban.DTO.userDTO;
import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequisitionDTO {
    private UUID id;
    private String name;
    private String email;
    private Long documentNumber;
}
