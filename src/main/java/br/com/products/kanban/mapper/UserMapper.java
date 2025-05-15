package br.com.products.kanban.mapper;

import org.modelmapper.ModelMapper;

import br.com.products.kanban.DTO.UserDTO;
import br.com.products.kanban.model.User;

public class UserMapper {
    private static final ModelMapper modelmMapper = new ModelMapper();
    public static UserDTO convertToDto(User user) {
        return modelmMapper.map(user, UserDTO.class);
    }
    public static User convertToEntity(UserDTO userDTO) {
        return modelmMapper.map(userDTO, User.class);
    }
};
