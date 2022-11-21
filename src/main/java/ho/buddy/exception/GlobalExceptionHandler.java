package ho.buddy.exception;

import static org.springframework.http.HttpStatus.*;

import ho.buddy.dto.ResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseDto<String> handleArgumentException(Exception e) {
        return new ResponseDto<String>(INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }

}

