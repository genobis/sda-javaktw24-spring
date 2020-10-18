package pl.sdacademy.majbaum.spring.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import pl.sdacademy.majbaum.spring.data.model.Foo;

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

    @Transactional
    public Foo changeName(long fooId, String name) {
        final Foo foo = fooRepository.findById(fooId)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));

        if (name == null) {
            return foo;
        }

        foo.setName(name);

        if (name.startsWith("a")) {
            throw new RuntimeException("Ooops!");
        }

        return foo;
    }
}
