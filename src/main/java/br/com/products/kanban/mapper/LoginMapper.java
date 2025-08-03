package br.com.products.kanban.mapper;

import org.modelmapper.ModelMapper;

import br.com.products.kanban.dto.LoginDTO;
import br.com.products.kanban.model.UserEntity;

/**
 * Mapper class for converting LoginDto to UserEntity using ModelMapper.
 */
public class LoginMapper {
    /**
     * ModelMapper instance used for object mapping.
     */
    public static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Maps a LoginDto object to a UserEntity object.
     *
     * @param loginDto The LoginDto to be mapped.
     * @return The mapped UserEntity.
     */
    public static UserEntity toUserEntityFromResponseDto(LoginDTO loginDto) {
        return modelMapper.map(loginDto, UserEntity.class);
    }
}