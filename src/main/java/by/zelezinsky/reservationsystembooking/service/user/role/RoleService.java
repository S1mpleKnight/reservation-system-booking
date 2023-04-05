package by.zelezinsky.reservationsystembooking.service.user.role;

import by.zelezinsky.reservationsystembooking.dto.user.role.RoleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoleService {

    RoleDto create(RoleDto dto);

    RoleDto update(UUID id, RoleDto dto);

    public Page<RoleDto> findAll(Pageable pageable);

    RoleDto findById(UUID id);

    void delete(UUID id);
}
