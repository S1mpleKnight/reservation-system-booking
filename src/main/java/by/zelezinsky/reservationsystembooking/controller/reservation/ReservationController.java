package by.zelezinsky.reservationsystembooking.controller.reservation;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.service.reservation.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.Reservation.BASE)
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping(Url.ID)
    public ReservationDto findById(@PathVariable UUID id) {
        return reservationService.findById(id);
    }

    @GetMapping
    public Page<ReservationDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return reservationService.findAll(pageable);
    }

    @PostMapping
    public ReservationDto create(@RequestBody @Valid ReservationDto dto) {
        return reservationService.create(dto);
    }

    @PutMapping(Url.ID)
    public ReservationDto update(@PathVariable UUID id, @RequestBody @Valid ReservationDto dto) {
        return reservationService.update(id, dto);
    }

    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        reservationService.delete(id);
    }
}
