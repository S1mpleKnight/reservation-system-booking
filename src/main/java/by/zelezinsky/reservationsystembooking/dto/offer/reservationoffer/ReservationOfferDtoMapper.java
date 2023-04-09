package by.zelezinsky.reservationsystembooking.dto.offer.reservationoffer;

import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.offer.event.EventDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.offer.info.AdditionalOfferInfoDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunit.ReservationUnitDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.reservation.reservationunittype.ReservationUnitTypeDtoMapper;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.OfferCategory;
import by.zelezinsky.reservationsystembooking.entity.offer.ReservationOffer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {EstablishmentDtoMapper.class, UserDtoMapper.class, EventDtoMapper.class,
        AdditionalOfferInfoDtoMapper.class, ReservationUnitTypeDtoMapper.class, ReservationUnitDtoMapper.class,
        ReservationUnitDtoMapper.class})
public interface ReservationOfferDtoMapper {

    String CATEGORY_IDS_QUALIFIER = "CATEGORY_IDS_QUALIFIER";

    @Mapping(target = "event", ignore = true)
    @Mapping(target = "eventId", ignore = true)
    @Mapping(target = "contact", ignore = true)
    @Mapping(target = "contactId", ignore = true)
    @Mapping(target = "establishment", ignore = true)
    @Mapping(target = "establishmentId", ignore = true)
    @Mapping(target = "info", ignore = true)
    @Mapping(target = "offerStatus", ignore = true)
    @Mapping(target = "types", ignore = true)
    @Mapping(target = "categories", ignore = true)
    @Mapping(target = "unitedParts", ignore = true)
    @Mapping(target = "reservationUnits", ignore = true)
    ReservationOffer toEntity(ReservationOfferDto dto);

    @Mapping(target = "categoryIds", qualifiedByName = CATEGORY_IDS_QUALIFIER)
    ReservationOfferDto toDto(ReservationOffer offer);

    default List<UUID> getCategoryIds(ReservationOffer offer) {
        return offer.getCategories().stream().map(OfferCategory::getId).collect(Collectors.toList());
    }
}
