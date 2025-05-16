package br.com.products.kanban.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.products.kanban.DTO.userDTO.UserRequisitionDTO;
import br.com.products.kanban.DTO.userDTO.UserResponseDTO;
import br.com.products.kanban.mapper.UserMapper;
import br.com.products.kanban.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private CryptoPasswordService cryptoPasswordService;

    public UserRequisitionDTO createUser(UserResponseDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            logger.info("Logg: Email already exists: {}", dto.getEmail());
            throw new RuntimeException("Email already exists");
        }
        dto.setPassword(cryptoPasswordService.encodePassword(dto.getPassword()));
        var userEntity = UserMapper.toUserEntityFromResponseDTO(dto);
        var savedEntity = userRepository.save(userEntity);
        return UserMapper.toRequisitionDTOFromUserEntity(savedEntity);
    }

    public Optional<UserRequisitionDTO> findById(UUID id) {
        return userRepository.findById(id).map(UserMapper::toRequisitionDTOFromUserEntity);
    }

    public List<UserRequisitionDTO> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toRequisitionDTOFromUserEntity)
                .collect(Collectors.toList());
    }
}
