package by.zelezinsky.reservationsystembooking.repository.offer;

import by.zelezinsky.reservationsystembooking.dto.filter.ReservationOfferFilter;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QReservationOfferRepository {

    Page<ReservationOffer> findAll(Pageable pageable, ReservationOfferFilter filter);
}
