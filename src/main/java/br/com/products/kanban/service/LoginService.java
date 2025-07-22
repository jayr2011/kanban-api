package br.com.products.kanban.service;

import br.com.products.kanban.dto.LoginDto;
import br.com.products.kanban.mapper.LoginMapper;
import br.com.products.kanban.model.UserEntity;
import br.com.products.kanban.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class LoginService {
    private final UserRepository userRepository;

    private final CryptoPasswordService cryptoPassword;

    public LoginService(UserRepository userRepository, CryptoPasswordService cryptoPassword) {
        this.userRepository = userRepository;
        this.cryptoPassword = cryptoPassword;
    }

    public boolean authenticate(LoginDto loginDTto){
        UserEntity loginData = LoginMapper.toUserEntityFromResponseDto(loginDTto);
        return userRepository.findByEmail(loginData.getEmail())
                .map(userFromDatabase -> cryptoPassword.matches(loginData.getPassword(), userFromDatabase.getPassword()))
                .orElse(false);
    }
}
