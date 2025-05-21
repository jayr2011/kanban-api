package br.com.products.kanban.mapper;

import org.modelmapper.ModelMapper;

import br.com.products.kanban.dto.LoginDto;
import br.com.products.kanban.model.UserEntity;

public class LoginMapper {
    public static final ModelMapper modelMapper = new ModelMapper();
    public static UserEntity toUserEntityFromResponseDto(LoginDto loginDto) {
        return modelMapper.map(loginDto, UserEntity.class);
    }
}
