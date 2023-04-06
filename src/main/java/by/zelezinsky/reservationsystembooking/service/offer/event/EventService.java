package by.zelezinsky.reservationsystembooking.service.offer.event;

import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface EventService {

    EventDto create(EventDto dto);

    EventDto update(UUID id, EventDto dto);

    EventDto findById(UUID id);

    Page<EventDto> findAll(Pageable pageable);

    void delete(UUID id);
}
