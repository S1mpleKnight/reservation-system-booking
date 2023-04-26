package by.zelezinsky.reservationsystembooking.controller.address;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.address.country.CountryDto;
import by.zelezinsky.reservationsystembooking.service.address.country.CountryService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.PublicKey;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.Country.BASE)
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public Page<CountryDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return countryService.findAll(pageable);
    }

    @PostMapping
    public CountryDto create(@RequestBody @Valid CountryDto dto) {
        return countryService.create(dto);
    }

    @PutMapping(Url.ID)
    public CountryDto update(@PathVariable UUID id, @RequestBody @Valid CountryDto dto) {
        return countryService.update(id, dto);
    }

    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        countryService.delete(id);
    }
}
