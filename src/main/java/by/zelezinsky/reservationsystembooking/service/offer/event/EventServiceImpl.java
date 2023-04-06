package by.zelezinsky.reservationsystembooking.service.offer.event;

import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDto;
import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.offer.EstablishmentRepository;
import by.zelezinsky.reservationsystembooking.repository.offer.EventRepository;
import by.zelezinsky.reservationsystembooking.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventDtoMapper eventDtoMapper;
    private final EstablishmentRepository establishmentRepository;
    private final UserRepository userRepository;

    @Override
    public EventDto create(EventDto dto) {
        Event entity = eventDtoMapper.toEntity(dto);
        return eventDtoMapper.toDto(eventRepository.save(entity));
    }

    @Override
    public EventDto update(UUID id, EventDto dto) {
        Event event = findEvent(id);
        event = eventDtoMapper.toEntity(event, dto);
        return eventDtoMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventDto findById(UUID id) {
        return eventDtoMapper.toDto(findEvent(id));
    }

    @Override
    public Page<EventDto> findAll(Pageable pageable) {
        return eventRepository.findAll(pageable).map(eventDtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        Event event = findEvent(id);
        eventRepository.delete(event);
    }

    private Event findEvent(UUID id) {
        return eventRepository.findById(id).orElseThrow(() -> new NotFoundException("Event", id.toString()));
    }
}
