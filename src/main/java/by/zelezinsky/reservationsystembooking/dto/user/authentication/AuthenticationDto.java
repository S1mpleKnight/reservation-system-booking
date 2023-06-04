package by.zelezinsky.reservationsystembooking.dto.user.authentication;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
public class AuthenticationDto {

    @NotBlank(message = "Username can not be empty")
    @Pattern(regexp = DtoConstants.USERNAME_REGEXP, message = "Username should be between 1 and 50 letters")
    private String username;

    @NotBlank
    private String password;
}
