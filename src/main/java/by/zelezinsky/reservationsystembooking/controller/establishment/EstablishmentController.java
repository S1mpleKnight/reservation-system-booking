package by.zelezinsky.reservationsystembooking.controller.establishment;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.filter.EstablishmentFilter;
import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.offer.establishment.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(Url.Establishment.BASE)
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @Secured(Authorities.VIEW_ESTABLISHMENT)
    @GetMapping(Url.ID)
    public EstablishmentDto findById(@PathVariable UUID id) {
        return establishmentService.findById(id);
    }

    @Secured(Authorities.VIEW_ESTABLISHMENT)
    @GetMapping
    public Page<EstablishmentDto> findAll(
            @RequestParam(required = false) Pageable pageable, EstablishmentFilter filter
    ) {
        return establishmentService.findAll(pageable, filter);
    }

    @Secured(Authorities.UPDATE_ESTABLISHMENT)
    @PostMapping
    public EstablishmentDto create(@RequestBody @Valid EstablishmentDto dto) {
        return establishmentService.create(dto);
    }

    @Secured(Authorities.UPDATE_ESTABLISHMENT)
    @PutMapping(Url.ID)
    public EstablishmentDto update(@PathVariable UUID id, @RequestBody @Valid EstablishmentDto dto) {
        return establishmentService.update(id, dto);
    }

    @Secured(Authorities.DELETE_ESTABLISHMENT)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        establishmentService.delete(id);
    }
}
