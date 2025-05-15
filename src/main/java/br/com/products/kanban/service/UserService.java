package br.com.products.kanban.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.products.kanban.DTO.UserDTO;
import br.com.products.kanban.mapper.UserMapper;
import br.com.products.kanban.repository.UserRepository;

@Service
public class UserService {
    @Autowired
   private UserRepository userRepository;

   private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
   private CryptoPasswordService cryptoPasswordService;

   public UserDTO createUser(UserDTO dto) {
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
                logger.warn("Email already exists: {}", dto.getEmail());
                throw new RuntimeException("Email already exists");
        }
        dto.setPassword(cryptoPasswordService.encodePassword(dto.getPassword()));
        var savedEntity = userRepository.save(UserMapper.convertToEntity(dto));
        var savedDto = UserMapper.convertToDto(savedEntity);
        savedDto.setPassword(null);
        return savedDto;
    }

   public List<UserDTO> findAllUserOptional() {
         return userRepository.findAll()
              .stream()
              .map(UserMapper::convertToDto)
              .collect(Collectors.toList());
   }

   public Optional<UserDTO> findById(UUID id) {
       return userRepository.findById(id).map(UserMapper::convertToDto);
   }

   public List<UserDTO> findAllUsers() {
         return userRepository.findAll()
              .stream()
              .map(UserMapper::convertToDto)
              .collect(Collectors.toList());
   }
}
