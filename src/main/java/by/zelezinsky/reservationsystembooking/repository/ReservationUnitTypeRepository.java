package by.zelezinsky.reservationsystembooking.repository;

import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservationUnitTypeRepository extends JpaRepository<ReservationUnitType, UUID> {

    Boolean existsByNameAndOfferId(String name, UUID offerId);

    Optional<ReservationUnitType> findByOfferAndId(ReservationOffer offer, UUID id);

    Page<ReservationUnitType> findAllByOffer(ReservationOffer offer, Pageable pageable);
}
