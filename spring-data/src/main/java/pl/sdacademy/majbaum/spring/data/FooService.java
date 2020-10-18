package pl.sdacademy.majbaum.spring.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.sdacademy.majbaum.spring.data.model.Foo;

import java.util.List;

@Service
public class FooService {

    private final FooRepository fooRepository;

    public FooService(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    public Page<Foo> getFoos(String query, Pageable pageable) {
        if (query == null) {
            return fooRepository.findAll(pageable);
        }
        else {
            return fooRepository.findByNameContainingIgnoreCaseOrderByName(query, pageable);
        }
    }

    public Foo addFoo(Foo foo) {
        return fooRepository.save(foo);
    }
}
