package pl.sdacademy.majbaum.spring.data.model;

import javax.persistence.*;

@Entity
@SequenceGenerator(name = "FooSeq", sequenceName = "foo_seq")
@Table(name = "foo")
public class Foo {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FooSeq")
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
