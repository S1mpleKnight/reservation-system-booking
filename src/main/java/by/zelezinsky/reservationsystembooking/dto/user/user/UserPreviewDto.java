package by.zelezinsky.reservationsystembooking.dto.user.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

import static by.zelezinsky.reservationsystembooking.dto.DtoConstants.*;

@Data
public class UserPreviewDto {

    @NotBlank(message = "Username can not be empty")
    @Pattern(regexp = USERNAME_REGEXP, message = "Username can not be empty")
    private String username;

    @NotBlank(message = "First name can not be empty")
    @Pattern(regexp = NAME_REGEXP, message = "First name can not be empty")
    private String firstname;

    @NotBlank(message = "Last name can not be empty")
    @Pattern(regexp = NAME_REGEXP, message = "Last name can not be empty")
    private String lastname;

    @NotBlank(message = "Birthday can not be empty")
    @JsonFormat(pattern = DATE_REGEXP, shape = JsonFormat.Shape.STRING)
    private LocalDate birthday;

    @NotBlank(message = "Password can not be empty")
    private String password;

    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Sex can not be empty")
    private Boolean isMale;
}
