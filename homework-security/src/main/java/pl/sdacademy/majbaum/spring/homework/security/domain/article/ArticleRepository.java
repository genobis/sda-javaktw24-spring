package pl.sdacademy.majbaum.spring.homework.security.domain.article;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import pl.sdacademy.majbaum.spring.homework.security.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article,Long> {
    @EntityGraph(attributePaths = {"author", "comments"})
    List<Article> findAll();

    @EntityGraph(attributePaths = {"author", "comments"})
    Optional<Article> findById(long id);

    int deleteAllById(long id);
}
