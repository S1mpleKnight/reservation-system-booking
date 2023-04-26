package by.zelezinsky.reservationsystembooking.repository.event;

import by.zelezinsky.reservationsystembooking.dto.filter.EventFilter;
import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface QEventRepository {

    Page<Event> findAll(Pageable pageable, EventFilter filter);
}
