package by.zelezinsky.reservationsystembooking.dto.user.user;

import by.zelezinsky.reservationsystembooking.dto.user.role.RoleDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.entity.user.UserRole;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring", uses = RoleDtoMapper.class)
public interface UserDtoMapper {

    String QUALIFIER_USER_ROLE = "QUALIFIER_USER_ROLE";

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "roleId", source = "user.role.id")
    UserDto toDto(User user);

    @Mapping(target = "roleName", qualifiedByName = QUALIFIER_USER_ROLE, source = "user")
    UserPreviewDto toPreviewDto(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "offers", ignore = true)
    User toEntity(UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "offers", ignore = true)
    User toEntity(@MappingTarget User user, UserDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "offers", ignore = true)
    @Mapping(target = "password", ignore = true)
    User toEntity(@MappingTarget User user, UserPreviewDto dto);

    @Mapping(target = "reservations", ignore = true)
    @Mapping(target = "role", ignore = true)
    @Mapping(target = "offers", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toEntity(UserPreviewDto dto);

    @Mapping(target = "password", ignore = true)
    User toEntity(User user);

    @Named(QUALIFIER_USER_ROLE)
    default UserRole getRole(User user) {
        String roleName = user.getRole().getName();
        return UserRole.valueOf(roleName);
    }
}
