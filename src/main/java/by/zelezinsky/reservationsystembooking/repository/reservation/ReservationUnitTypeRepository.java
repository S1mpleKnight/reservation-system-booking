package by.zelezinsky.reservationsystembooking.repository.reservation;

import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationUnitTypeRepository extends JpaRepository<ReservationUnitType, UUID> {

    Boolean existsByNameAndOfferId(String name, UUID offerId);
}
