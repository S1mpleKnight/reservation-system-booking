package by.zelezinsky.reservationsystembooking.controller.reservation;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDto;
import by.zelezinsky.reservationsystembooking.service.reservation.unit.ReservationUnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(Url.ReservationOffer.Unit.FULL)
@RequiredArgsConstructor
public class ReservationUnitController {

    private final ReservationUnitService reservationUnitService;

    @GetMapping(Url.UUID)
    public ReservationUnitDto findById(@PathVariable UUID id, @PathVariable UUID uuid) {
        return reservationUnitService.findById(id, uuid);
    }

    @GetMapping
    public Page<ReservationUnitDto> findAll(@PathVariable UUID id, @RequestParam(required = false) Pageable pageable) {
        return reservationUnitService.findAll(id, pageable);
    }

    @PostMapping
    public ReservationUnitDto create(@PathVariable UUID id, @RequestBody @Valid ReservationUnitDto dto) {
        return reservationUnitService.create(id, dto);
    }

    @PutMapping(Url.UUID)
    public ReservationUnitDto update(
            @PathVariable UUID id, @PathVariable UUID uuid, @RequestBody @Valid ReservationUnitDto dto
    ) {
        return reservationUnitService.update(id, uuid, dto);
    }

    @DeleteMapping(Url.UUID)
    public void delete(@PathVariable UUID id, @PathVariable UUID uuid) {
        reservationUnitService.delete(id, uuid);
    }
}
