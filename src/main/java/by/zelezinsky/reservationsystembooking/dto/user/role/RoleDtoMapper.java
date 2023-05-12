package by.zelezinsky.reservationsystembooking.dto.user.role;

import by.zelezinsky.reservationsystembooking.entity.user.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface RoleDtoMapper {

    @Mapping(target = "id", ignore = true)
    Role toEntity(RoleDto dto);

    RoleDto toDto(Role role);

    @Mapping(target = "id", ignore = true)
    Role toEntity(@MappingTarget Role role, RoleDto dto);
}
