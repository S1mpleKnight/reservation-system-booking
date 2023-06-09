package by.zelezinsky.reservationsystembooking.service.offer.reservationoffer;

import by.zelezinsky.reservationsystembooking.dto.filter.ReservationOfferFilter;
import by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer.ReservationOfferDto;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOfferStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ReservationOfferService {

    ReservationOfferDto create(ReservationOfferDto dto);

    ReservationOfferDto update(UUID id, ReservationOfferDto dto);

    ReservationOfferDto findById(UUID id);

    Page<ReservationOfferDto> findAll(Pageable pageable, ReservationOfferFilter filter);

    void delete(UUID id);

    ReservationOfferDto changeStatusAndContact(UUID id, UUID contactId, ReservationOfferStatus status);
}
