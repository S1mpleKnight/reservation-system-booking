package by.zelezinsky.reservationsystembooking.controller.offer;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.filter.ReservationOfferFilter;
import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDto;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOfferStatus;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.offer.reservationoffer.ReservationOfferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.ReservationOffer.BASE)
public class ReservationOfferController {

    private final ReservationOfferService reservationOfferService;

//    @Secured(Authorities.VIEW_RESERVATION_OFFER)
    @GetMapping(Url.ID)
    public ReservationOfferDto findById(@PathVariable UUID id) {
        return reservationOfferService.findById(id);
    }

//    @Secured(Authorities.VIEW_RESERVATION_OFFER)
    @GetMapping
    public Page<ReservationOfferDto> findAll(
            @RequestParam(required = false) Pageable pageable, ReservationOfferFilter filter
    ) {
        return reservationOfferService.findAll(pageable, filter);
    }

    @Secured(Authorities.UPDATE_RESERVATION_OFFER)
    @PostMapping
    public ReservationOfferDto create(@RequestBody @Valid ReservationOfferDto dto) {
        return reservationOfferService.create(dto);
    }

    @Secured(Authorities.UPDATE_RESERVATION_OFFER)
    @PutMapping(Url.ID)
    public ReservationOfferDto update(@PathVariable UUID id, @RequestBody @Valid ReservationOfferDto dto) {
        return reservationOfferService.update(id, dto);
    }

    @Secured(Authorities.DELETE_RESERVATION_OFFER)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        reservationOfferService.delete(id);
    }

    @Secured(Authorities.UPDATE_RESERVATION_OFFER)
    @PatchMapping(Url.ID)
    public ReservationOfferDto changeStatusAndContact(
            @PathVariable UUID id, @RequestParam UUID contactId, @RequestParam ReservationOfferStatus status
    ) {
        return reservationOfferService.changeStatusAndContact(id, contactId, status);
    }
}
