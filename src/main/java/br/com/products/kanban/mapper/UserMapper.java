package br.com.products.kanban.mapper;

import org.modelmapper.ModelMapper;

import br.com.products.kanban.DTO.userDTO.UserRequisitionDTO;
import br.com.products.kanban.DTO.userDTO.UserResponseDTO;
import br.com.products.kanban.model.User;

/**
 * The UserMapper class provides utility methods for converting between various user-related objects.
 * These methods use ModelMapper to automatically map properties between source and target classes.
 *
 * <p>
 * This class supports the following conversions:
 * <ul>
 *   <li>
 *     Converting a {@link UserResponseDTO} to a {@link User} entity using {@code toUserEntityFromResponseDTO(UserResponseDTO)}.
 *   </li>
 *   <li>
 *     Converting a {@link UserRequisitionDTO} to a {@link User} entity using {@code toUserEntityFromRequisitionDTO(UserRequisitionDTO)}.
 *   </li>
 *   <li>
 *     Converting a {@link User} entity to a {@link UserRequisitionDTO} using {@code toRequisitionDTOFromUserEntity(User)}.
 *   </li>
 * </ul>
 * </p>
 */
public class UserMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static User toUserEntityFromResponseDTO(Object savedEntity) {
        return modelMapper.map(savedEntity, User.class);
    }

    public static User toUserEntityFromRequisitionDTO(UserResponseDTO dto) {
        return modelMapper.map(dto, User.class);
    }

    public static UserRequisitionDTO toRequisitionDTOFromUserEntity(User user) {
        return modelMapper.map(user, UserRequisitionDTO.class);
    }
}
