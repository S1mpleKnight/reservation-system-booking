package by.zelezinsky.reservationsystembooking.dto.offer.event;

import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {EstablishmentDtoMapper.class})
public interface EventDtoMapper {

    EventDto toDto(Event event);

    Event toEntity(EventDto dto);

    @Mapping(target = "id", ignore = true)
    Event toEntity(@MappingTarget Event event, EventDto dto);
}
