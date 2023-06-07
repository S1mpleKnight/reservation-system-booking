package by.zelezinsky.reservationsystembooking.repository.event;

import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID>, QEventRepository {
    Optional<Event> findByContactAndTitleAndStartDateAndEndDateAndTime(
            User contact, String title, LocalDate startDate, LocalDate endDate, LocalTime time);
}
