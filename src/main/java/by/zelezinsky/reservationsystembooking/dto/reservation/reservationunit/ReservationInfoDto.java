package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit;

import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserPreviewDto;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReservationInfoDto {
    private ReservationUnitDto unit;
    private ReservationOfferDto offer;
    private UserPreviewDto user;
    private ReservationDto reservation;
}
