package by.zelezinsky.reservationsystembooking.dto.offer.category;

import by.zelezinsky.reservationsystembooking.entity.offer.OfferCategory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OfferCategoryDtoMapper {

    OfferCategoryDto toDto(OfferCategory category);

    @Mapping(target = "id", ignore = true)
    OfferCategory toEntity(@MappingTarget OfferCategory category, OfferCategoryDto dto);

    @Mapping(target = "id", ignore = true)
    OfferCategory toEntity(OfferCategoryDto dto);
}
