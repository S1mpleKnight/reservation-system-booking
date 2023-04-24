package by.zelezinsky.reservationsystembooking.repository.reservation;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationUnitedPartRepository extends JpaRepository<ReservationUnitedPart, UUID> {

    Optional<ReservationUnitedPart> findByOfferAndId(ReservationOffer offer, UUID id);

    Page<ReservationUnitedPart> findAllByOffer(ReservationOffer offer, Pageable pageable);
}
