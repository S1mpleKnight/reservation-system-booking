package by.zelezinsky.reservationsystembooking.dto.user.authentication;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class AuthenticationDto {

    @NotBlank(message = "Username can not be empty")
    @Pattern(regexp = DtoConstants.NAME_REGEXP, message = "Username should be between 1 and 50 letters")
    private String username;

    @NotBlank
    private String password;
}
