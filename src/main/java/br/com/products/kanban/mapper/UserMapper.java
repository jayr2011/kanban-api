package br.com.products.kanban.mapper;

import org.modelmapper.ModelMapper;

import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.dto.user.UserViewDto;
import br.com.products.kanban.model.UserEntity;

/**
 * Mapper class for converting between User DTOs and UserEntity using ModelMapper.
 */
public class UserMapper {
    /**
     * ModelMapper instance used for object mapping.
     */
    private static final ModelMapper modelMapper = new ModelMapper();

    /**
     * Maps a UserCreationRequestDto to a UserEntity.
     *
     * @param dto The UserCreationRequestDto to be mapped.
     * @return The mapped UserEntity.
     */
    public static UserEntity toEntity(UserCreationRequestDto dto) {
        return modelMapper.map(dto, UserEntity.class);
    }

    /**
     * Maps a UserEntity to a UserViewDto.
     *
     * @param user The UserEntity to be mapped.
     * @return The mapped UserViewDto.
     */
    public static UserViewDto toViewDTO(UserEntity user) {
        return modelMapper.map(user, UserViewDto.class);
    }
}