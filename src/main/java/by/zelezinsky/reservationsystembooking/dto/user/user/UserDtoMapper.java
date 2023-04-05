package by.zelezinsky.reservationsystembooking.dto.user.user;

import by.zelezinsky.reservationsystembooking.dto.user.role.RoleDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = RoleDtoMapper.class)
public interface UserDtoMapper {

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roleId", source = "role.id")
    UserDto toDto(User user);

    UserPreviewDto toPreviewDto(User user);

    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(@MappingTarget User user, UserDto dto);

    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "role", ignore = true)
    User toEntity(UserPreviewDto dto);
}
