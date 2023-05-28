package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.UUID;

@Data
@Valid
public class ReservationUnitTypeDto {

    private UUID id;

    @NotBlank(message = "Name can not be empty")
    @Pattern(regexp = DtoConstants.NAME_REGEXP, message = "Name should be between 1 and 50 letters" )
    private String name;
}
