package by.zelezinsky.reservationsystembooking.controller.offer;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDto;
import by.zelezinsky.reservationsystembooking.service.reservation.unittype.ReservationUnitTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(Url.ReservationOffer.UnitType.FULL)
@RequiredArgsConstructor
public class ReservationUnitTypeController {

    private final ReservationUnitTypeService reservationUnitTypeService;

    @GetMapping(Url.UUID)
    public ReservationUnitTypeDto findById(@PathVariable UUID id, @PathVariable UUID uuid) {
        return reservationUnitTypeService.findById(id, uuid);
    }

    @GetMapping
    public Page<ReservationUnitTypeDto> findAll(
            @PathVariable UUID id, @RequestParam(required = false) Pageable pageable
    ) {
        return reservationUnitTypeService.findAll(id, pageable);
    }

    @PostMapping
    public ReservationUnitTypeDto create(@PathVariable UUID id, @RequestBody @Valid ReservationUnitTypeDto dto) {
        return reservationUnitTypeService.create(id, dto);
    }

    @PutMapping(Url.UUID)
    public ReservationUnitTypeDto update(
            @PathVariable UUID id, @PathVariable UUID uuid, @RequestBody @Valid ReservationUnitTypeDto dto
    ) {
        return reservationUnitTypeService.update(id, uuid, dto);
    }

    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID id, @PathVariable UUID uuid) {
        reservationUnitTypeService.delete(id, uuid);
    }
}
