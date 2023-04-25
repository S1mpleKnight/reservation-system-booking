package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

    @NotNull(message = "United part falg can not be empty")
    private Boolean hasUnitedPart;

    private UUID reservationUnitedPartId;

    @NotBlank(message = "Offer can not be null")
    private UUID offerId;

    @NotNull(message = "Time flag can not be empty")
    private Boolean hasTime;

    @JsonFormat(pattern = DtoConstants.TIME_REGEXP, shape = JsonFormat.Shape.STRING)
    private LocalTime time;

    @NotNull(message = "Order flag can not be empty")
    private Boolean hasOrderNumber;

    private Integer orderNumber;

    @NotNull(message = "Reservation type flag can not be empty")
    private Boolean hasReservationUnitType;

    private ReservationUnitTypeDto reservationUnitType;

    private UUID reservationUnitTypeId;

    private UUID reservationId;

    private ReservationDto reservation;
}
