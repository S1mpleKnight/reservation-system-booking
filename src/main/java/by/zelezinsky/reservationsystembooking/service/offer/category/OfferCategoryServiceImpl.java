package by.zelezinsky.reservationsystembooking.service.offer.category;

import by.zelezinsky.reservationsystembooking.dto.offer.category.OfferCategoryDto;
import by.zelezinsky.reservationsystembooking.dto.offer.category.OfferCategoryDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.offer.OfferCategory;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.offer.OfferCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OfferCategoryServiceImpl implements OfferCategoryService {

    private OfferCategoryRepository offerCategoryRepository;
    private OfferCategoryDtoMapper offerCategoryDtoMapper;

    @Override
    public OfferCategoryDto create(OfferCategoryDto dto) {
        if (offerCategoryRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Category with that name already exists");
        }
        OfferCategory entity = offerCategoryDtoMapper.toEntity(dto);
        return offerCategoryDtoMapper.toDto(offerCategoryRepository.save(entity));
    }

    @Override
    public OfferCategoryDto update(UUID id, OfferCategoryDto dto) {
        OfferCategory category = findCategory(id);
        if (offerCategoryRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Category with that name already exists");
        }
        category = offerCategoryDtoMapper.toEntity(category, dto);
        return offerCategoryDtoMapper.toDto(offerCategoryRepository.save(category));
    }

    @Override
    public OfferCategoryDto findById(UUID id) {
        return offerCategoryDtoMapper.toDto(findCategory(id));
    }

    @Override
    public Page<OfferCategoryDto> findAll(Pageable pageable) {
        return offerCategoryRepository.findAll(pageable).map(offerCategoryDtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        OfferCategory category = findCategory(id);
        offerCategoryRepository.delete(category);
    }

    private OfferCategory findCategory(UUID id) {
        return offerCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offer category", id.toString()));
    }
}
