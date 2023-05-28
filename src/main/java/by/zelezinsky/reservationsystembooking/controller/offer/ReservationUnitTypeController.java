package by.zelezinsky.reservationsystembooking.controller.offer;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.reservation.unittype.ReservationUnitTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequestMapping(Url.ReservationOffer.UnitType.FULL)
@RequiredArgsConstructor
public class ReservationUnitTypeController {

    private final ReservationUnitTypeService reservationUnitTypeService;

    @Secured(Authorities.VIEW_RESERVATION_UNIT_TYPE)
    @GetMapping(Url.UUID)
    public ReservationUnitTypeDto findById(@PathVariable UUID id, @PathVariable UUID uuid) {
        return reservationUnitTypeService.findById(id, uuid);
    }

    @Secured(Authorities.VIEW_RESERVATION_UNIT_TYPE)
    @GetMapping
    public Page<ReservationUnitTypeDto> findAll(
            @PathVariable UUID id, @RequestParam(required = false) Pageable pageable
    ) {
        return reservationUnitTypeService.findAll(id, pageable);
    }

    @Secured(Authorities.UPDATE_RESERVATION_UNIT_TYPE)
    @PostMapping
    public ReservationUnitTypeDto create(@PathVariable UUID id, @RequestBody @Valid ReservationUnitTypeDto dto) {
        return reservationUnitTypeService.create(id, dto);
    }

    @Secured(Authorities.UPDATE_RESERVATION_UNIT_TYPE)
    @PutMapping(Url.UUID)
    public ReservationUnitTypeDto update(
            @PathVariable UUID id, @PathVariable UUID uuid, @RequestBody @Valid ReservationUnitTypeDto dto
    ) {
        return reservationUnitTypeService.update(id, uuid, dto);
    }

    @Secured(Authorities.DELETE_RESERVATION_UNIT_TYPE)
    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID id, @PathVariable UUID uuid) {
        reservationUnitTypeService.delete(id, uuid);
    }
}
