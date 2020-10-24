package pl.sdacademy.majbaum.spring.security;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.majbaum.spring.security.model.User;

public interface UserRepository extends JpaRepository<User,String> { }
