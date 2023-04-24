package by.zelezinsky.reservationsystembooking.controller.user;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import by.zelezinsky.reservationsystembooking.service.user.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping(Url.User.BASE)
public class UserController {

    private final UserService userService;

    @GetMapping(Url.ID)
    public UserDto findById(@PathVariable UUID id) {
        return userService.findById(id);
    }

    @GetMapping
    public Page<UserDto> findAll(@RequestParam(required = false) Pageable pageable) {
        return userService.findAll(pageable);
    }

    @PostMapping
    public UserDto create(@RequestBody @Valid UserDto dto) {
        return userService.create(dto);
    }

    @PutMapping(Url.ID)
    public UserDto update(@PathVariable UUID id, @RequestBody @Valid UserDto dto) {
        return userService.update(id, dto);
    }

    @DeleteMapping(Url.ID)
    public void delete(@PathVariable UUID id) {
        userService.delete(id);
    }
}
