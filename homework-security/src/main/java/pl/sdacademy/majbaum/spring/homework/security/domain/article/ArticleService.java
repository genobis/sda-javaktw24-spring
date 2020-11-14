package pl.sdacademy.majbaum.spring.homework.security.domain.article;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserData;
import pl.sdacademy.majbaum.spring.homework.security.domain.user.UserRepository;
import pl.sdacademy.majbaum.spring.homework.security.model.Article;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static pl.sdacademy.majbaum.spring.homework.security.configuration.security.UserAuthorities.CAN_DELETE_ANY_ARTICLE;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final UserData userData;
    private final Clock clock;

    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository, UserData userData, Clock clock) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.userData = userData;
        this.clock = clock;
    }

    public List<Article> getArticles() {
        return articleRepository.findAll();
    }

    public Optional<Article> getArticle(long id) {
        return articleRepository.findById(id);
    }

    @Transactional
    public Article createArticle(String title, String content) {
        final Article article = new Article();
        article.setTitle(title);
        article.setContent(content);
        article.setAuthor(userRepository.getOne(userData.getId()));
        article.setPublicationDate(LocalDateTime.now(clock));
        articleRepository.save(article);
        return article;
    }

    @Transactional
    public Optional<Article> updateArticle(long id, String title, String content) {
        final Optional<Article> articleOptional = getOwnedArticleOrThrow(id);
        articleOptional.ifPresent(article -> {
            article.setTitle(title);
            article.setContent(content);
            article.setPublicationDate(LocalDateTime.now(clock));
        });
        return articleOptional;
    }

    @Transactional
    public boolean deleteArticle(long id) {
        if (!userData.hasAuthority(CAN_DELETE_ANY_ARTICLE)) {
            if (getOwnedArticleOrThrow(id).isEmpty()) {
                return false;
            }
        }

        return articleRepository.deleteAllById(id) > 0;
    }

    /**
     * Loads an article by given identifier or throws an exception if not found.
     * @param id article id
     * @return article or empty optional if not found
     * @throws AccessDeniedException if current user doesn't own the article
     */
    public Optional<Article> getOwnedArticleOrThrow(long id) {
        final Optional<Article> articleOpt = getArticle(id);
        if(articleOpt.isEmpty()) {
            return Optional.empty();
        }

        if (!articleOpt.get().getAuthor().getUserName().equals(userData.getUsername())) {
            throw new AccessDeniedException(String.format("User \"%s\" doesn't own article with id: %d", userData.getUsername(), id));
        }

        return articleOpt;
    }
}
