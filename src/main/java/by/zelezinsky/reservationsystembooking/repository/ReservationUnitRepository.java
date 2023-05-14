package by.zelezinsky.reservationsystembooking.repository;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitedPart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationUnitRepository extends JpaRepository<ReservationUnit, UUID> {

    Optional<ReservationUnit> findByOfferAndId(ReservationOffer offer, UUID uuid);

    Page<ReservationUnit> findAllByOffer(ReservationOffer offer, Pageable pageable);

    Boolean existsByReservationUnitedPart(ReservationUnitedPart part);
}
