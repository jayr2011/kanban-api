package br.com.products.kanban.service;

import br.com.products.kanban.dto.LoginDTO; 
import br.com.products.kanban.mapper.LoginMapper;
import br.com.products.kanban.model.UserEntity;
import br.com.products.kanban.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service responsible for user authentication and login operations.
 */
@Getter
@Setter
@Service
public class LoginService {
    /**
     * Repository for accessing user data.
     */
    private final UserRepository userRepository;

    /**
     * Service for password encryption and verification.
     */
    private final CryptoPasswordService cryptoPassword;

    /**
     * Service for JWT token operations.
     */
    private final JwtService jwtService;

    /**
     * Constructs a LoginService with required dependencies.
     *
     * @param userRepository Repository for user data.
     * @param cryptoPassword Service for password encryption and verification.
     * @param jwtService Service for JWT token operations.
     */
    public LoginService(UserRepository userRepository, CryptoPasswordService cryptoPassword, JwtService jwtService) {
        this.userRepository = userRepository;
        this.cryptoPassword = cryptoPassword;
        this.jwtService = jwtService;
    }

    /**
     * Authenticates a user based on login credentials.
     *
     * @param loginDTto Data transfer object containing login credentials.
     * @return true if authentication is successful, false otherwise.
     */
    public boolean authenticate(LoginDTO loginDTto){
        UserEntity loginData = LoginMapper.toUserEntityFromResponseDto(loginDTto);
        return userRepository.findByEmail(loginData.getEmail())
                .map(userFromDatabase -> cryptoPassword.matches(loginData.getPassword(), userFromDatabase.getPassword()))
                .orElse(false);
    }

    /**
     * Performs login and returns a JWT token if authentication is successful.
     *
     * @param loginDto Data transfer object containing login credentials.
     * @return Optional containing JWT token if login is successful, empty otherwise.
     */
    public Optional<String> login(LoginDTO loginDto) {
        return userRepository.findByEmail(loginDto.getEmail())
                .filter(user -> cryptoPassword.matches(loginDto.getPassword(), user.getPassword()))
                .map(user -> jwtService.generateToken(user.getId()));
    }
}