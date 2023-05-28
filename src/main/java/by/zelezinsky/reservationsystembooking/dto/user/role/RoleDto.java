package by.zelezinsky.reservationsystembooking.dto.user.role;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import jakarta.validation.Valid;
import lombok.Data;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.util.UUID;

@Data
@Valid
public class RoleDto {

    private UUID id;

    @NotBlank(message = "Role name can not be empty")
    @Pattern(regexp = DtoConstants.NAME_REGEXP, message = "Role name should be between 8 and 50 letters")
    private String name;
}
