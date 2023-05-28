package by.zelezinsky.reservationsystembooking.controller.reservation;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.reservation.unit.ReservationUnitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping(Url.ReservationOffer.Unit.FULL)
@RequiredArgsConstructor
public class ReservationUnitController {

    private final ReservationUnitService reservationUnitService;

    @Secured(Authorities.VIEW_RESERVATION_UNIT)
    @GetMapping(Url.UUID)
    public ReservationUnitDto findById(@PathVariable UUID id, @PathVariable UUID uuid) {
        return reservationUnitService.findById(id, uuid);
    }

    @Secured(Authorities.VIEW_RESERVATION_UNIT)
    @GetMapping
    public Page<ReservationUnitDto> findAll(@PathVariable UUID id, @RequestParam(required = false) Pageable pageable) {
        return reservationUnitService.findAll(id, pageable);
    }

    @Secured(Authorities.UPDATE_RESERVATION_UNIT)
    @PostMapping
    public ReservationUnitDto create(@PathVariable UUID id, @RequestBody @Valid ReservationUnitDto dto) {
        return reservationUnitService.create(id, dto);
    }

    @Secured(Authorities.UPDATE_RESERVATION_UNIT)
    @PutMapping(Url.UUID)
    public ReservationUnitDto update(
            @PathVariable UUID id, @PathVariable UUID uuid, @RequestBody @Valid ReservationUnitDto dto
    ) {
        return reservationUnitService.update(id, uuid, dto);
    }

    @Secured(Authorities.DELETE_RESERVATION_UNIT)
    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID id, @PathVariable UUID uuid) {
        reservationUnitService.delete(id, uuid);
    }
}
