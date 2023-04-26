package by.zelezinsky.reservationsystembooking.repository.offer;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationOfferRepository extends JpaRepository<ReservationOffer, UUID>,
        QReservationOfferRepository {
}
