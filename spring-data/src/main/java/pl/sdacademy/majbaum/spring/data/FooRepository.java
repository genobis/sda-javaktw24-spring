package pl.sdacademy.majbaum.spring.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.majbaum.spring.data.model.Foo;

public interface FooRepository extends JpaRepository<Foo,Long> {
    Page<Foo> findByNameContainingIgnoreCaseOrderByName(
            String name,
            Pageable pageable
    );
}
