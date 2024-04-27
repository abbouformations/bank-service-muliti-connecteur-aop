package ma.formations.multiconnector.presentation.rest;

import ma.formations.multiconnector.dtos.exception.ErrorResponse;
import ma.formations.multiconnector.service.exception.BusinessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        return new ResponseEntity<>(ErrorResponse.builder().
                message("Business error").
                details(ex.getBindingResult().getAllErrors().
                        stream().
                        map(exception -> exception.getDefaultMessage()).
                        collect(Collectors.toList())).
                build(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(ErrorResponse.builder().
                message("Business Exception").
                details(List.of(ex.getMessage())).
                build(), HttpStatus.BAD_REQUEST);
    }
}
