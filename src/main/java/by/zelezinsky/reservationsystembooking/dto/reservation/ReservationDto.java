package by.zelezinsky.reservationsystembooking.dto.reservation;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

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

    @DateTimeFormat(pattern = DtoConstants.TIME_REGEXP, iso = DateTimeFormat.ISO.TIME)
    private LocalTime reservationTime;

    @DateTimeFormat(pattern = DtoConstants.DATE_REGEXP, iso = DateTimeFormat.ISO.DATE)
    private LocalDate reservationDate;

    @NotNull(message = "Status can not be empty")
    private ReservationStatus status;

    private List<ReservationUnitDto> units;

    @NotNull(message = "Reservation units can not be empty")
    @NotEmpty(message = "Reservation units can not be empty")
    private List<UUID> unitIds;
}
