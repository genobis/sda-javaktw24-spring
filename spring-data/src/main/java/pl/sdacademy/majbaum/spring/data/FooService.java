package pl.sdacademy.majbaum.spring.data;

import org.springframework.stereotype.Service;
import pl.sdacademy.majbaum.spring.data.model.Foo;

import java.util.List;

@Service
public class FooService {

    private final FooRepository fooRepository;

    public FooService(FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }

    public List<Foo> getFoos() {
        return fooRepository.findAll();
    }

    public Foo addFoo(Foo foo) {
        return fooRepository.save(foo);
    }
}
