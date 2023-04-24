package by.zelezinsky.reservationsystembooking.controller.reservation;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart.ReservationUnitedPartDto;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import by.zelezinsky.reservationsystembooking.service.reservation.reservationunitedpart.ReservationUnitedPartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Url.ReservationOffer.UnitedPart.FULL)
@RequiredArgsConstructor
public class ReservationUnitedPartController {

    private final ReservationUnitedPartService reservationUnitedPartService;

    @GetMapping
    public ReservationUnitedPartDto findById(@PathVariable )
}
