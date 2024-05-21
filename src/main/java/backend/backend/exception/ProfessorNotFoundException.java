package backend.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ProfessorNotFoundException extends RuntimeException{
    public ProfessorNotFoundException(String message) {
        super(message);
    }
}
