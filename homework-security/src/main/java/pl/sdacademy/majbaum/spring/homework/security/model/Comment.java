package pl.sdacademy.majbaum.spring.homework.security.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@SequenceGenerator(name = "CommentSeq", sequenceName = "comment_seq")
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CommentSeq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
