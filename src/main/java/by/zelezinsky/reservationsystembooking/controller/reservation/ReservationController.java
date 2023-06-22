package by.zelezinsky.reservationsystembooking.controller.reservation;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDto;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationInfoDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.reservation.ReservationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.Reservation.BASE)
public class ReservationController {

    private final ReservationService reservationService;

    @Secured(Authorities.VIEW_RESERVATION)
    @GetMapping(Url.ID)
    public ReservationDto findById(@PathVariable UUID id) {
        return reservationService.findById(id);
    }

    @Secured(Authorities.VIEW_RESERVATION)
    @GetMapping
    public Page<ReservationDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return reservationService.findAll(pageable);
    }

    @Secured(Authorities.VIEW_RESERVATION)
    @GetMapping(Url.Reservation.PERSONAL)
    public List<ReservationInfoDto> findAllPersonal(Principal principal) {
        return reservationService.findAllPersonal(principal.getName());
    }

    @Secured(Authorities.UPDATE_RESERVATION)
    @PostMapping
    public ReservationDto create(@RequestBody @Valid ReservationDto dto) {
        return reservationService.create(dto);
    }

    @Secured(Authorities.UPDATE_RESERVATION)
    @PutMapping(Url.ID)
    public ReservationDto update(@PathVariable UUID id, @RequestBody @Valid ReservationDto dto) {
        return reservationService.update(id, dto);
    }

    @Secured(Authorities.DELETE_RESERVATION)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        reservationService.delete(id);
    }
}
