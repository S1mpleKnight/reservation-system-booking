package by.zelezinsky.reservationsystembooking.dto.user.user;

import by.zelezinsky.reservationsystembooking.entity.user.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

import static by.zelezinsky.reservationsystembooking.dto.DtoConstants.*;

@Data
public class UserPreviewDto {

    @Pattern(regexp = USERNAME_REGEXP, message = "Username must be between 8 and 50 letters and numbers")
    private String username;

    @NotBlank(message = "First name must be between 1 and 50 letters")
    @Pattern(regexp = NAME_REGEXP, message = "First name must be between 1 and 50 letters")
    private String firstname;

    @NotBlank(message = "Last name must be between 1 and 50 letters")
    @Pattern(regexp = NAME_REGEXP, message = "Last name must be between 1 and 50 letters")
    private String lastname;

    @NotNull(message = "Birthday can not be empty")
    @DateTimeFormat(pattern = DATE_REGEXP)
    private LocalDate birthday;

    @NotNull(message = "Password can not be empty")
    @NotBlank(message = "Password can not be empty")
    private String password;

    @NotBlank(message = "Invalid email")
    @Email(message = "Invalid email")
    private String email;

    @NotNull(message = "Sex can not be empty")
    private Boolean isMale;

    @NotNull(message = "Role can not be empty")
    private UserRole roleName;
}
