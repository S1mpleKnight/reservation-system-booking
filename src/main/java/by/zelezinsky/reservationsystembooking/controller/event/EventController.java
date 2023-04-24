package by.zelezinsky.reservationsystembooking.controller.event;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDto;
import by.zelezinsky.reservationsystembooking.service.offer.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.Event.BASE)
public class EventController {

    private final EventService eventService;

    @GetMapping(Url.ID)
    public EventDto findById(@PathVariable UUID id) {
        return eventService.findById(id);
    }

    @GetMapping
    public Page<EventDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return eventService.findAll(pageable);
    }

    @PostMapping
    public EventDto create(@RequestBody @Valid EventDto dto) {
        return eventService.create(dto);
    }

    @PutMapping(Url.ID)
    public EventDto update(@PathVariable UUID id, @RequestBody @Valid EventDto dto) {
        return eventService.update(id, dto);
    }

    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        eventService.delete(id);
    }
}
