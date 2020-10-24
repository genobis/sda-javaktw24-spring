package pl.sdacademy.majbaum.spring.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.majbaum.spring.data.model.Foo;

public interface FooRepository extends JpaRepository<Foo,Long> {
    @EntityGraph(attributePaths = "bar")
    Page<Foo> findByNameContainingIgnoreCaseOrderByName(
            String name,
            Pageable pageable
    );

    @EntityGraph(attributePaths = "bar")
    Page<Foo> findAll(Pageable pageable);
}
