package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype;

import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnitType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReservationUnitTypeDtoMapper {

    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "offerId", ignore = true)
    ReservationUnitType toEntity(ReservationUnitTypeDto dto);


    @Mapping(target = "id", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "offerId", ignore = true)
    ReservationUnitType toEntity(@MappingTarget ReservationUnitType type, ReservationUnitTypeDto dto);

    ReservationUnitTypeDto toDto(ReservationUnitType reservationUnitType);
}
