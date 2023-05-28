package by.zelezinsky.reservationsystembooking.controller.exception;

import by.zelezinsky.reservationsystembooking.dto.exception.ExceptionMessageDto;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.JwtAuthenticationException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ExceptionMessageDto> badRequest(RuntimeException e) {
        return new ResponseEntity<>(new ExceptionMessageDto(e.getMessage(),
                HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value()),
                HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ExceptionMessageDto> notFound(RuntimeException e) {
        return new ResponseEntity<>(new ExceptionMessageDto(e.getMessage(), HttpStatus.NOT_FOUND,
                HttpStatus.NOT_FOUND.value()),
                HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler({JwtAuthenticationException.class, BadCredentialsException.class})
    public ResponseEntity<ExceptionMessageDto> forbidden(RuntimeException e) {
        return new ResponseEntity<>(new ExceptionMessageDto(e.getMessage(), HttpStatus.FORBIDDEN,
                HttpStatus.FORBIDDEN.value()),
                HttpStatus.FORBIDDEN);
    }
}
