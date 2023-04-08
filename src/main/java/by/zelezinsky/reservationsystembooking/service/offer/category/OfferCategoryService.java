package by.zelezinsky.reservationsystembooking.service.offer.category;

import by.zelezinsky.reservationsystembooking.dto.offer.category.OfferCategoryDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface OfferCategoryService {

    OfferCategoryDto create(OfferCategoryDto dto);

    OfferCategoryDto update(UUID id, OfferCategoryDto dto);

    OfferCategoryDto findById(UUID id);

    Page<OfferCategoryDto> findAll(Pageable pageable);

    void delete(UUID id);
}
