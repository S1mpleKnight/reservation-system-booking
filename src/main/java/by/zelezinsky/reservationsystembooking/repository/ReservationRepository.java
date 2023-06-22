package by.zelezinsky.reservationsystembooking.repository;

import by.zelezinsky.reservationsystembooking.entity.reservation.Reservation;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findAllByUser(User user);
}
