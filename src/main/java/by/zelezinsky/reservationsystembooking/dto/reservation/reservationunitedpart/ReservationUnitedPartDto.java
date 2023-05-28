package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Valid
public class ReservationUnitedPartDto {

    private UUID id;

    @NotNull(message = "Name can not be empty")
    @Pattern(regexp = DtoConstants.NAME_REGEXP, message = "Name should be between 1 and 50 letters")
    private String name;

    private List<ReservationUnitDto> units;

    private List<UUID> unitIds;

    private List<ReservationUnitedPartDto> parts;

    private UUID parentId;

    @NotNull(message = "Reservation offer can not be empty")
    private UUID offerId;
}
