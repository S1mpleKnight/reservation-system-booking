package by.zelezinsky.reservationsystembooking.repository.reservation;

import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationUnitRepository extends JpaRepository<ReservationUnit, UUID> {
}