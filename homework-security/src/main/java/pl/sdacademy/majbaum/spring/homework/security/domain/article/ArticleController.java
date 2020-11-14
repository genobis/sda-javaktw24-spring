package pl.sdacademy.majbaum.spring.homework.security.domain.article;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import pl.sdacademy.majbaum.spring.homework.security.model.Article;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Validated
@Controller
@RequestMapping("/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ModelAndView getArticles(ModelAndView mav) {
        final List<Article> articles = articleService.getArticles();
        mav
                .addObject("title", "Artykuły")
                .addObject("articles", articles);
        mav.setViewName("articles");
        return mav;
    }

    @GetMapping("/{id}")
    public ModelAndView getArticle(@PathVariable long id, ModelAndView mav) {
        final Article article = articleService.getArticle(id)
                .orElseThrow(() -> notFoundException(id));

        mav
                .addObject("title", article.getTitle())
                .addObject("article", article);

        mav.setViewName("article");
        return mav;
    }

    @GetMapping("/add")
    public ModelAndView addArticle(ModelAndView mav) {
        mav.addObject("title", "Dodaj artykuł");
        mav.setViewName("article-form");
        return mav;
    }

    @PostMapping("/add")
    public String addArticle(
            @NotBlank @RequestParam String title,
            @NotBlank @RequestParam String content
    ) {
        final Article article = articleService.createArticle(title, content);
        return "redirect:/articles/" + article.getId();
    }

    @GetMapping("/{id}/edit")
    public ModelAndView editArticle(@PathVariable long id, ModelAndView mav) {
        final Article article = articleService.getOwnedArticleOrThrow(id)
                .orElseThrow(() -> notFoundException(id));

        mav
                .addObject("title", article.getTitle())
                .addObject("article", article);

        mav.setViewName("article-form");
        return mav;
    }

    @PostMapping("/{id}/edit")
    public String editArticle(
            @PathVariable long id,
            @NotBlank @RequestParam String title,
            @NotBlank @RequestParam String content
    ) {
        final Article article = articleService.updateArticle(id, title, content)
                .orElseThrow(() -> notFoundException(id));

        return "redirect:/articles/" + article.getId();
    }

    @PostMapping("/{id}/delete")
    public String deleteArticle(@PathVariable long id) {
        if (!articleService.deleteArticle(id)) {
            throw notFoundException(id);
        }
        return "redirect:/articles/";
    }

    private ResponseStatusException notFoundException(long id) {
        return new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Nie znaleziono artykułu o id: " + id
        );
    }

}
