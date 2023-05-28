package by.zelezinsky.reservationsystembooking.controller.event;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.offer.event.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.Event.BASE)
public class EventController {

    private final EventService eventService;

    @Secured(Authorities.VIEW_EVENT)
    @GetMapping(Url.ID)
    public EventDto findById(@PathVariable UUID id) {
        return eventService.findById(id);
    }

    @Secured(Authorities.VIEW_EVENT)
    @GetMapping
    public Page<EventDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return eventService.findAll(pageable);
    }

    @Secured(Authorities.UPDATE_EVENT)
    @PostMapping
    public EventDto create(@RequestBody @Valid EventDto dto) {
        return eventService.create(dto);
    }

    @Secured(Authorities.UPDATE_EVENT)
    @PutMapping(Url.ID)
    public EventDto update(@PathVariable UUID id, @RequestBody @Valid EventDto dto) {
        return eventService.update(id, dto);
    }

    @Secured(Authorities.DELETE_EVENT)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        eventService.delete(id);
    }
}
