package br.com.products.kanban.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.products.kanban.repository.UserRepository;

@Service
public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
   private CryptoPasswordService cryptoPassword;

    public boolean authenticate(String email, String password) {
       return userRepository.findByEmail(email)
           .map(user -> cryptoPassword.matches(password, user.getPassword()))
           .orElse(false);
   }
}
