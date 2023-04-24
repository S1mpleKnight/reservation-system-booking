package by.zelezinsky.reservationsystembooking.controller.establishment;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.offer.establishment.EstablishmentDto;
import by.zelezinsky.reservationsystembooking.service.offer.establishment.EstablishmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(Url.Establishment.BASE)
@RequiredArgsConstructor
public class EstablishmentController {

    private final EstablishmentService establishmentService;

    @GetMapping(Url.ID)
    public EstablishmentDto findById(@PathVariable UUID id) {
        return establishmentService.findById(id);
    }

    @GetMapping
    public Page<EstablishmentDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return establishmentService.findAll(pageable);
    }

    @PostMapping
    public EstablishmentDto create(@RequestBody @Valid EstablishmentDto dto) {
        return establishmentService.create(dto);
    }

    @PutMapping(Url.ID)
    public EstablishmentDto update(@PathVariable UUID id, @RequestBody @Valid EstablishmentDto dto) {
        return establishmentService.update(id, dto);
    }

    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        establishmentService.delete(id);
    }
}
