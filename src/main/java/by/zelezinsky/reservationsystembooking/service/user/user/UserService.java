package by.zelezinsky.reservationsystembooking.service.user.user;

import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface UserService {

    UserDto create(UserDto dto);

    UserDto update(UUID id, UserDto dto);

    UserDto findById(UUID id);

    Page<UserDto> findAll(Pageable pageable);

    void delete(UUID id);
}
