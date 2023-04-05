package by.zelezinsky.reservationsystembooking.dto.offer.event;

import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.Event;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {EstablishmentDtoMapper.class})
public interface EventDtoMapper {

    EventDto toDto(Event event);

    @Mapping(target = "offers", ignore = true)
    @Mapping(target = "establishment", ignore = true)
    Event toEntity(EventDto dto);
}
