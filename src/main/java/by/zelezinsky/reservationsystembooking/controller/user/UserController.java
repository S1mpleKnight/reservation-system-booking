package by.zelezinsky.reservationsystembooking.controller.user;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import by.zelezinsky.reservationsystembooking.security.Authorities;
import by.zelezinsky.reservationsystembooking.service.user.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;


import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(Url.User.BASE)
public class UserController {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Secured(Authorities.VIEW_USER)
    @GetMapping(Url.ID)
    public UserDto findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @Secured(Authorities.VIEW_USER)
    @GetMapping
    public Page<UserDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return userService.findAll(pageable);
    }

    @Secured(Authorities.UPDATE_USER)
    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto dto) {
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return userService.create(dto);
    }

    @Secured(Authorities.UPDATE_USER)
    @PutMapping(Url.ID)
    public UserDto update(@PathVariable UUID id, @RequestBody @Valid UserDto dto) {
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return userService.update(id, dto);
    }

    @Secured(Authorities.DELETE_USER)
    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
