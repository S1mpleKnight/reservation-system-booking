package by.zelezinsky.reservationsystembooking.service.user.role;

import by.zelezinsky.reservationsystembooking.dto.user.role.RoleDto;
import by.zelezinsky.reservationsystembooking.dto.user.role.RoleDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.user.Role;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleDtoMapper roleDtoMapper;

    @Override
    public RoleDto create(RoleDto dto) {
        if (roleRepository.existsByName(dto.getName())) {
            throw new BadRequestException("Role with that name already exists");
        }
        Role entity = roleDtoMapper.toEntity(dto);
        return roleDtoMapper.toDto(roleRepository.save(entity));
    }

    @Override
    public RoleDto update(UUID id, RoleDto dto) {
        Role role = findRole(id);
        if (roleRepository.existsByName(dto.getName())){
            throw new BadRequestException("Role with that name already exists");
        }
        Role entity = roleDtoMapper.toEntity(role, dto);
        return roleDtoMapper.toDto(roleRepository.save(entity));
    }

    @Override
    public Page<RoleDto> findAll(Pageable pageable) {
        if (Objects.isNull(pageable)) {
            pageable = Pageable.unpaged();
        }
        return roleRepository.findAll(pageable).map(roleDtoMapper::toDto);
    }

    @Override
    public RoleDto findById(UUID id) {
        return roleDtoMapper.toDto(findRole(id));
    }

    @Override
    public void delete(UUID id) {
        Role role = findRole(id);
        roleRepository.delete(role);
    }

    private Role findRole(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role", id.toString()));
    }
}
