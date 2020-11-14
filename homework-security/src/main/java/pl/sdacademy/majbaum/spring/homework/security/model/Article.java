package pl.sdacademy.majbaum.spring.homework.security.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@SequenceGenerator(name = "ArticleSeq", sequenceName = "article_seq")
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ArticleSeq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @Column(name = "publication_date")
    private LocalDateTime publicationDate;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    @OrderBy("publicationDate desc")
    private List<Comment> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
