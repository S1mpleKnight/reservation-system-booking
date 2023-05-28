package by.zelezinsky.reservationsystembooking.dto.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ExceptionMessageDto {
    private String message;
    private HttpStatus status;
    private Integer code;
}
