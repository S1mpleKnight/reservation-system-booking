package by.zelezinsky.reservationsystembooking.controller.offer;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.offer.category.OfferCategoryDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.offer.category.OfferCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequestMapping(Url.Category.BASE)
@RestController
@RequiredArgsConstructor
public class OfferCategoryController {

    private final OfferCategoryService offerCategoryService;

    @Secured(Authorities.VIEW_OFFER_CATEGORY)
    @GetMapping(Url.ID)
    public OfferCategoryDto findById(@PathVariable UUID id) {
        return offerCategoryService.findById(id);
    }

    @Secured(Authorities.VIEW_OFFER_CATEGORY)
    @GetMapping
    public Page<OfferCategoryDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return offerCategoryService.findAll(pageable);
    }

    @Secured(Authorities.UPDATE_OFFER_CATEGORY)
    @PostMapping
    public OfferCategoryDto create(@RequestBody @Valid OfferCategoryDto dto) {
        return offerCategoryService.create(dto);
    }

    @Secured(Authorities.UPDATE_OFFER_CATEGORY)
    @PutMapping(Url.ID)
    public OfferCategoryDto update(@PathVariable UUID id, @RequestBody @Valid OfferCategoryDto dto) {
        return offerCategoryService.update(id, dto);
    }

    @Secured(Authorities.DELETE_OFFER_CATEGORY)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        offerCategoryService.delete(id);
    }
}
