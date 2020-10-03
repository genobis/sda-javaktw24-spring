package pl.sdacademy.majbaum.spring.web;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

@Controller
public class DifferentResponsesController {
    private final Random random;

    public DifferentResponsesController(Random random) {
        this.random = random;
    }

    @ResponseStatus(HttpStatus.GONE)
    @GetMapping("/rsp-gone-1")
    public void responseStatusGone() {}

    @GetMapping("/rsp-gone-2")
    public ResponseEntity<Void> responseEntityGone() {
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    //Wyjątek łapie się w Springowy widok do obsługi błędów
    @GetMapping("/rsp-gone-3")
    public void responseExceptionGone() {
        throw new ResponseStatusException(HttpStatus.GONE);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @GetMapping("/rsp-empty-random-1")
    public void responseAnnotationNoContent() {
        if (!random.nextBoolean()) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/rsp-empty-random-2")
    public ResponseEntity<Void> responseEntityNoContent() {
        if (!random.nextBoolean()) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
