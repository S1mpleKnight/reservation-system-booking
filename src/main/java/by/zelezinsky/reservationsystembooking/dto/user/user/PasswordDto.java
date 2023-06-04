package by.zelezinsky.reservationsystembooking.dto.user.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class PasswordDto {
    @NotNull(message = "Old password can not be empty")
    @NotBlank(message = "Old password can not be empty")
    private String oldPassword;
    @NotNull(message = "New password can not be empty")
    @NotBlank(message = "New password can not be empty")
    private String newPassword;
    @NotNull(message = "Repeat new password can not be empty")
    @NotBlank(message = "Repeat new password can not be empty")
    private String repeatNewPassword;
}
