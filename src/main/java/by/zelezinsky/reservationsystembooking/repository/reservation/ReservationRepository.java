package by.zelezinsky.reservationsystembooking.repository.reservation;

import by.zelezinsky.reservationsystembooking.entity.reservation.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {
}
