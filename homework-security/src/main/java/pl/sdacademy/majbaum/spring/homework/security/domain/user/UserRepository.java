package pl.sdacademy.majbaum.spring.homework.security.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.majbaum.spring.homework.security.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
}
