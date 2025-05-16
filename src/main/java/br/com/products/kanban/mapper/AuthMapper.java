package br.com.products.kanban.mapper;

import br.com.products.kanban.DTO.LoginDTO;
import br.com.products.kanban.model.User;

public class AuthMapper {
    public static User toUserEntityFromResponseDTO(LoginDTO dto) {
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }
}
