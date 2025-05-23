package br.com.products.kanban.service;

import br.com.products.kanban.dto.user.UserCreationRequestDto;
import br.com.products.kanban.dto.user.UserViewDto;
import br.com.products.kanban.mapper.UserMapper;
import br.com.products.kanban.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {
    private final UserRepository userRepository;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final CryptoPasswordService cryptoPasswordService;

    public UserService(UserRepository userRepository, CryptoPasswordService cryptoPasswordService) {
        this.userRepository = userRepository;
        this.cryptoPasswordService = cryptoPasswordService;
    }

    public UserViewDto createUser(UserCreationRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            logger.info("Logg: Email already exists: {}", dto.getEmail());
            throw new RuntimeException("Email already exists");
        }
        dto.setPassword(cryptoPasswordService.encodePassword(dto.getPassword()));
        var userEntity = UserMapper.toEntity(dto);
        var savedEntity = userRepository.save(userEntity);
        return UserMapper.toViewDTO(savedEntity);
    }

    public Optional<UserViewDto> findById(UUID id) {
        return userRepository.findById(id).map(UserMapper::toViewDTO);
    }

    public List<UserViewDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserMapper::toViewDTO)
                .collect(Collectors.toList());
    }
}
