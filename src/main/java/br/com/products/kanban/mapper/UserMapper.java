package br.com.products.kanban.mapper;

import org.modelmapper.ModelMapper;

import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.dto.user.UserViewDto;
import br.com.products.kanban.model.UserEntity;
public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static UserEntity toEntity(UserCreationRequestDto dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    public static UserViewDto toViewDTO(UserEntity user) {
        return modelMapper.map(user, UserViewDto.class);
    }
}
