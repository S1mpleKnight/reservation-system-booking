package by.zelezinsky.reservationsystembooking.service.offer.event;

import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDto;
import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.event.EventRepository;
import by.zelezinsky.reservationsystembooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;
    private final EventDtoMapper eventDtoMapper;
    private final UserRepository userRepository;

    @Override
    public EventDto create(EventDto dto) {
        User user = findUser(dto.getContactId());
        Event entity = eventDtoMapper.toEntity(dto);
        if (eventRepository.findByContactAndTitleAndStartDateAndEndDateAndTime(user, entity.getTitle(),
                entity.getStartDate(), entity.getEndDate(), entity.getTime()).isPresent()) {
            throw new BadRequestException("Such event is already exist");
        }
        entity.setContact(user);
        return eventDtoMapper.toDto(eventRepository.save(entity));
    }

    @Override
    public EventDto update(UUID id, EventDto dto) {
        Event event = findEvent(id);
        User user = findUser(dto.getContactId());
        event = eventDtoMapper.toEntity(event, dto);
        event.setContact(user);
        if (eventRepository.findByContactAndTitleAndStartDateAndEndDateAndTime(event.getContact(), event.getTitle(),
                event.getStartDate(), event.getEndDate(), event.getTime()).isPresent()) {
            throw new BadRequestException("Such event is already exist");
        }
        return eventDtoMapper.toDto(eventRepository.save(event));
    }

    @Override
    public EventDto findById(UUID id) {
        return eventDtoMapper.toDto(findEvent(id));
    }

    @Override
    public Page<EventDto> findAll(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
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

    private User findUser(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", id.toString()));
    }
}
