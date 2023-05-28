package by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.dto.offer.category.OfferCategoryDto;
import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDto;
import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDto;
import by.zelezinsky.reservationsystembooking.dto.offer.info.AdditionalOfferInfoDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart.ReservationUnitedPartDto;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import by.zelezinsky.reservationsystembooking.entity.offer.OrderReservationType;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOfferStatus;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

import static by.zelezinsky.reservationsystembooking.dto.DtoConstants.DATE_REGEXP;
import static by.zelezinsky.reservationsystembooking.dto.DtoConstants.TIME_REGEXP;

@Data
@Valid
public class ReservationOfferDto {

    private UUID id;

    @NotNull(message = "Name can not be empty")
    @Pattern(message = "Name should be between 1 and 50 letters", regexp = DtoConstants.NAME_REGEXP)
    private String name;

    @NotNull(message = "Event flag can not be empty")
    private Boolean hasEvent;

    private EventDto event;

    private UUID eventId;

    private List<ReservationUnitDto> reservationUnits;

    private List<ReservationUnitedPartDto> unitedParts;

    @NotNull(message = "Reservation date can not be empty")
    @JsonFormat(pattern = DATE_REGEXP, shape = JsonFormat.Shape.STRING)
    private LocalDate reservationDate;

    @NotNull(message = "Time flag can not be empty")
    private Boolean hasTime;

    @JsonFormat(pattern = TIME_REGEXP, shape = JsonFormat.Shape.STRING)
    private LocalTime reservationTime;

    private List<OfferCategoryDto> categories;

    private List<UUID> categoryIds;

    @NotNull(message = "Info flag can not be empty")
    private Boolean hasAdditionalInfo;

    @NotBlank(message = "Reservation type can no be empty")
    private ReservationType reservationType;

    @NotBlank(message = "Reservation order type can not be empty")
    private OrderReservationType orderType;

    @NotBlank(message = "Reservation offer status can not be empty")
    private ReservationOfferStatus offerStatus;

    private UserDto contact;

    @NotNull(message = "Contact can not be empty")
    private UUID contactId;

    @NotNull(message = "Establishment flag can not be empty")
    private Boolean hasEstablishment;

    private EstablishmentDto establishment;

    private UUID establishmentId;

    private AdditionalOfferInfoDto additionalOfferInfo;
}
