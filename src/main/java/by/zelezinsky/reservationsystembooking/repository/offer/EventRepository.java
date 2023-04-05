package by.zelezinsky.reservationsystembooking.repository.offer;

import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EventRepository extends JpaRepository<Event, UUID> {
}
