package br.com.products.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.products.kanban.DTO.LoginDTO;
import br.com.products.kanban.repository.UserRepository;
import br.com.products.kanban.model.User;
import br.com.products.kanban.mapper.AuthMapper;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
   private CryptoPasswordService cryptoPassword;

    public boolean authenticate(LoginDTO loginDTO) {
        User loginData = AuthMapper.toUserEntityFromResponseDTO(loginDTO);
        return userRepository.findByEmail(loginData.getEmail())
                .map(userFromDatabase -> cryptoPassword.matches(loginData.getPassword(), userFromDatabase.getPassword()))
                .orElse(false);
    }
}
