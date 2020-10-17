package pl.sdacademy.majbaum.spring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.majbaum.spring.data.model.Foo;

import java.util.List;

public interface FooRepository extends JpaRepository<Foo,Long> {
    List<Foo> findByNameContainingIgnoreCaseOrderByName(String name);
}
