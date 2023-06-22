package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart.ReservationUnitedPartDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalTime;
import java.util.UUID;

@Data
@Valid
public class ReservationUnitDto {

    private UUID id;

    @Pattern(regexp = DtoConstants.DESCRIPTION_REGEXP, message = "Description should be between 0 and 255 letters " +
            "and numbers")
    private String description;

    @NotBlank(message = "Name can not be empty")
    @Pattern(regexp = DtoConstants.NAME_REGEXP, message = "Name should be between 1 and 50 letters")
    private String name;

    @NotNull(message = "United part flag can not be empty")
    private Boolean hasUnitedPart;

    private UUID reservationUnitedPartId;

    @NotNull(message = "Offer can not be null")
    private UUID offerId;

    @NotNull(message = "Time flag can not be empty")
    private Boolean hasTime;

    @DateTimeFormat(pattern = DtoConstants.TIME_REGEXP)
    private LocalTime time;

    @NotNull(message = "Order flag can not be empty")
    private Boolean hasOrderNumber;

    private Integer orderNumber;

    @NotNull(message = "Reservation type flag can not be empty")
    private Boolean hasReservationUnitType;

    private ReservationUnitedPartDto reservationUnitedPart;

    private ReservationUnitTypeDto reservationUnitType;

    private UUID reservationUnitTypeId;

    private UUID reservationId;

    private ReservationDto reservation;
}
