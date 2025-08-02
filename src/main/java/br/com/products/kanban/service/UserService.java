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

/**
 * Service responsible for user management operations such as creation, retrieval, and listing.
 */
@Service
public class UserService {
    /**
     * Repository for accessing user data.
     */
    private final UserRepository userRepository;

    /**
     * Logger for logging user service events.
     */
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    /**
     * Service for password encryption.
     */
    private final CryptoPasswordService cryptoPasswordService;

    /**
     * Constructs a UserService with required dependencies.
     *
     * @param userRepository Repository for user data.
     * @param cryptoPasswordService Service for password encryption.
     */
    public UserService(UserRepository userRepository, CryptoPasswordService cryptoPasswordService) {
        this.userRepository = userRepository;
        this.cryptoPasswordService = cryptoPasswordService;
    }

    /**
     * Creates a new user if the email does not already exist.
     *
     * @param dto Data transfer object containing user creation information.
     * @return UserViewDto representing the created user.
     * @throws RuntimeException if the email already exists.
     */
    public UserViewDto createUser(UserCreationRequestDto dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            logger.info("Logg: Email already exists: {}", dto.getEmail());
            throw new RuntimeException("Email already exists");
        }
        dto.setPassword(cryptoPasswordService.encodePassword(dto.getPassword()));
        return UserMapper.toViewDTO(userRepository.save(UserMapper.toEntity(dto)));
    }

    /**
     * Finds a user by their unique identifier.
     *
     * @param id UUID of the user.
     * @return Optional containing UserViewDto if found, empty otherwise.
     */
    public Optional<UserViewDto> findById(UUID id) {
        return userRepository.findById(id).map(UserMapper::toViewDTO);
    }

    /**
     * Retrieves all users in the system.
     *
     * @return List of UserViewDto representing all users.
     */
    public List<UserViewDto> findAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toViewDTO).toList();
    }

    public void deleteAll() {
        userRepository.deleteAll();
    }
}