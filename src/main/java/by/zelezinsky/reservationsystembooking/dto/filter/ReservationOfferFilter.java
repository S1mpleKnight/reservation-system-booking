package by.zelezinsky.reservationsystembooking.dto.filter;

import by.zelezinsky.reservationsystembooking.dto.DtoConstants;
import by.zelezinsky.reservationsystembooking.entity.offer.OrderReservationType;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOfferStatus;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
public class ReservationOfferFilter {

    private Boolean hasEvent;
    private List<UUID> eventIds;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.DATE_REGEXP)
    private LocalDate dateFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.DATE_REGEXP)
    private LocalDate dateTo;

    private Boolean hasTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.TIME_REGEXP)
    private LocalTime timeFrom;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DtoConstants.TIME_REGEXP)
    private LocalTime timeTo;

    private List<UUID> categoryIds;
    private List<ReservationType> reservationTypes;
    private List<OrderReservationType> orderReservationTypes;
    private List<UUID> contactIds;
    private Boolean hasEstablishment;
    private List<UUID> establishmentIds;
    private List<ReservationOfferStatus> statuses;
}
