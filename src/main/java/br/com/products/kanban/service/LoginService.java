package br.com.products.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.products.kanban.dto.LoginDto;
import br.com.products.kanban.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import br.com.products.kanban.model.UserEntity;
import br.com.products.kanban.mapper.LoginMapper;

@Getter
@Setter
@Service
public class LoginService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CryptoPasswordService cryptoPassword;

    public boolean authenticate(LoginDto loginDTto){
        UserEntity loginData = LoginMapper.toUserEntityFromResponseDto(loginDTto);
        return userRepository.findByEmail(loginData.getEmail())
                .map(userFromDatabase -> cryptoPassword.matches(loginData.getPassword(), userFromDatabase.getPassword()))
                .orElse(false);
    }
}
