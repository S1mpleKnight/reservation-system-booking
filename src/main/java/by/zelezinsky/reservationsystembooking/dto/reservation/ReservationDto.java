package by.zelezinsky.reservationsystembooking.dto.reservation;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@Valid
public class ReservationDto {

    private UUID id;

    private UserDto user;

    @NotNull(message = "User can not be null")
    private UUID userId;

    @JsonFormat(pattern = DtoConstants.TIME_REGEXP, shape = JsonFormat.Shape.STRING)
    private LocalTime reservationTime;

    @JsonFormat(pattern = DtoConstants.DATE_REGEXP, shape = JsonFormat.Shape.STRING)
    private LocalDate reservationDate;

    @NotNull(message = "Status can not be empty")
    private ReservationStatus status;

    private List<ReservationUnitDto> units;

    @NotNull(message = "Reservation units can not be empty")
    @NotEmpty(message = "Reservation units can not be empty")
    private List<UUID> unitIds;
}
