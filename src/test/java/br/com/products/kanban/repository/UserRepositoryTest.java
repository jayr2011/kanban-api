package br.com.products.kanban.repository;

import br.com.products.kanban.model.UserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

@DataMongoTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void deveSalvarEBuscarUsuarioPorEmail() {
        UserEntity user = new UserEntity();
        user.setId("azer1234");
        user.setName("Teste");
        user.setEmail("teste@email.com");
        user.setPassword("senha");

        userRepository.save(user);

        Optional<UserEntity> encontrado = userRepository.findByEmail("teste@email.com");
        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getName()).isEqualTo("Teste");
    }
}
