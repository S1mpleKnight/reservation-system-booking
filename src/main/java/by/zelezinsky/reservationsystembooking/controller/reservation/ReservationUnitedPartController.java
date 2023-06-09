package by.zelezinsky.reservationsystembooking.controller.reservation;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunitedpart.ReservationUnitedPartDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.reservation.unitedpart.ReservationUnitedPartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping(Url.ReservationOffer.UnitedPart.FULL)
@RequiredArgsConstructor
public class ReservationUnitedPartController {

    private final ReservationUnitedPartService reservationUnitedPartService;

    @Secured(Authorities.VIEW_RESERVATION_UNITED_PART)
    @GetMapping(Url.UUID)
    public ReservationUnitedPartDto findById(@PathVariable UUID id, @PathVariable UUID uuid) {
        return reservationUnitedPartService.findById(id, uuid);
    }

    @Secured(Authorities.VIEW_RESERVATION_UNITED_PART)
    @GetMapping
    public Page<ReservationUnitedPartDto> findAll(
            @PathVariable UUID id, @RequestParam(required = false) Pageable pageable
    ) {
        return reservationUnitedPartService.findAll(id, pageable);
    }

    @Secured(Authorities.UPDATE_RESERVATION_UNITED_PART)
    @PostMapping
    public ReservationUnitedPartDto create(@PathVariable UUID id, @RequestBody @Valid ReservationUnitedPartDto dto) {
        return reservationUnitedPartService.create(id, dto);
    }

    @Secured(Authorities.DELETE_RESERVATION_UNITED_PART)
    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID id, @PathVariable UUID uuid) {
        reservationUnitedPartService.delete(id, uuid);
    }
}
