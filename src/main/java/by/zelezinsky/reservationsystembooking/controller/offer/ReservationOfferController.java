package by.zelezinsky.reservationsystembooking.controller.offer;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDto;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOfferStatus;
import by.zelezinsky.reservationsystembooking.service.offer.reservationoffer.ReservationOfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.ReservationOffer.BASE)
public class ReservationOfferController {

    private final ReservationOfferService reservationOfferService;

    @GetMapping(Url.ID)
    public ReservationOfferDto findById(@PathVariable UUID id) {
        return reservationOfferService.findById(id);
    }

    @GetMapping
    public Page<ReservationOfferDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return reservationOfferService.findAll(pageable);
    }

    @PostMapping
    public ReservationOfferDto create(@RequestBody @Valid ReservationOfferDto dto) {
        return reservationOfferService.create(dto);
    }

    @PutMapping(Url.ID)
    public ReservationOfferDto update(@PathVariable UUID id, @RequestBody @Valid ReservationOfferDto dto) {
        return reservationOfferService.update(id, dto);
    }

    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        reservationOfferService.delete(id);
    }

    @PatchMapping(Url.ID)
    public ReservationOfferDto changeStatusAndContact(
            @PathVariable UUID id, @RequestParam UUID contactId, @RequestParam ReservationOfferStatus status
    ) {
        return reservationOfferService.changeStatusAndContact(id, contactId, status);
    }
}
