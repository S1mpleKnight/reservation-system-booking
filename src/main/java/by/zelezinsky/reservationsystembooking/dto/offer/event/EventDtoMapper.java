package by.zelezinsky.reservationsystembooking.dto.offer.event;

import by.zelezinsky.reservationsystembooking.dto.user.user.UserDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {UserDtoMapper.class})
public interface EventDtoMapper {

    EventDto toDto(Event event);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contact", ignore = true)
    Event toEntity(EventDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "contact", ignore = true)
    Event toEntity(@MappingTarget Event event, EventDto dto);

    @AfterMapping
    default void correct(@MappingTarget Event event) {
        if (!event.getHasTime()) {
            event.setTime(null);
        }
        if (!event.getHasEndDate()) {
            event.setEndDate(null);
        }
    }
}
