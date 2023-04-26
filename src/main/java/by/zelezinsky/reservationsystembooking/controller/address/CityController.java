package by.zelezinsky.reservationsystembooking.controller.address;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.address.city.CityDto;
import by.zelezinsky.reservationsystembooking.service.address.city.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.City.BASE)
public class CityController {

    private final CityService cityService;

    @GetMapping
    public Page<CityDto> findAll(
            @RequestParam(required = false) Pageable pageable, @RequestParam(required = false) UUID countryId
    ) {
        return cityService.findAll(pageable, countryId);
    }

    @PostMapping
    public CityDto create(@RequestBody @Valid CityDto dto) {
        return cityService.create(dto);
    }

    @PutMapping(Url.ID)
    public CityDto update(@PathVariable UUID id, @RequestBody @Valid CityDto dto) {
        return cityService.update(id, dto);
    }

    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        cityService.delete(id);
    }
}
