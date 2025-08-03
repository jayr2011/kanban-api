package br.com.products.kanban.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.com.products.kanban.model.UserEntity;

import java.util.Optional;
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findByEmail(String email);
}
