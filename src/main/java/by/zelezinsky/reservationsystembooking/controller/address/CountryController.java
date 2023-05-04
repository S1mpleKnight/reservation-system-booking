package by.zelezinsky.reservationsystembooking.controller.address;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.address.country.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.Country.BASE)
public class CountryController {

    private final CountryService countryService;

    @Secured(Authorities.VIEW_COUNTRY)
    @GetMapping
    public Page<CountryDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return countryService.findAll(pageable);
    }

    @Secured(Authorities.UPDATE_COUNTRY)
    @PostMapping
    public CountryDto create(@RequestBody @Valid CountryDto dto) {
        return countryService.create(dto);
    }

    @Secured(Authorities.UPDATE_COUNTRY)
    @PutMapping(Url.ID)
    public CountryDto update(@PathVariable UUID id, @RequestBody @Valid CountryDto dto) {
        return countryService.update(id, dto);
    }

    @Secured(Authorities.DELETE_COUNTRY)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        countryService.delete(id);
    }
}
