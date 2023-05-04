package by.zelezinsky.reservationsystembooking.controller.role;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.user.role.RoleDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.user.role.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping(Url.Role.BASE)
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @Secured(Authorities.VIEW_ROLE)
    @GetMapping(Url.ID)
    public RoleDto findById(@PathVariable UUID id) {
        return roleService.findById(id);
    }

    @Secured(Authorities.VIEW_ROLE)
    @GetMapping
    public Page<RoleDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return roleService.findAll(pageable);
    }

    @Secured(Authorities.UPDATE_ROLE)
    @PostMapping
    public RoleDto create(@RequestBody @Valid RoleDto dto) {
        return roleService.create(dto);
    }

    @Secured(Authorities.UPDATE_ROLE)
    @PutMapping(Url.ID)
    public RoleDto update(@PathVariable UUID id, @RequestBody @Valid RoleDto dto) {
        return roleService.update(id, dto);
    }

    @Secured(Authorities.DELETE_ROLE)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        roleService.delete(id);
    }
}
