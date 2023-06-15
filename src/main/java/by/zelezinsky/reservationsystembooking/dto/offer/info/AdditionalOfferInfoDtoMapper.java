package by.zelezinsky.reservationsystembooking.dto.offer.info;

import by.zelezinsky.reservationsystembooking.entity.offer.AdditionalOfferInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdditionalOfferInfoDtoMapper {

    @Mapping(target = "offerId", source = "offer.id")
    AdditionalOfferInfoDto toDto(AdditionalOfferInfo info);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "offer", ignore = true)
    AdditionalOfferInfo toEntity(AdditionalOfferInfoDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "offer", ignore = true)
    AdditionalOfferInfo toEntity(@MappingTarget AdditionalOfferInfo info, AdditionalOfferInfoDto dto);
}
