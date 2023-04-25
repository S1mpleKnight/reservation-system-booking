package by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit;

import by.zelezinsky.reservationsystembooking.dto.reservation.ReservationDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.reservation.ReservationUnit;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = {ReservationUnitTypeDtoMapper.class, ReservationDtoMapper.class})
public interface ReservationUnitDtoMapper {

    ReservationUnitDto toDto(ReservationUnit unit);

    @Mapping(target = "reservationUnitType", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "reservationUnitedPart", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "reservationId", ignore = true)
    ReservationUnit toEntity(ReservationUnitDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservationUnitType", ignore = true)
    @Mapping(target = "offer", ignore = true)
    @Mapping(target = "reservationUnitedPart", ignore = true)
    @Mapping(target = "reservation", ignore = true)
    @Mapping(target = "reservationId", ignore = true)
    ReservationUnit toEntity(@MappingTarget ReservationUnit unit, ReservationUnitDto dto);
}
