package by.zelezinsky.reservationsystembooking.service.user.user;

import by.zelezinsky.reservationsystembooking.dto.user.user.UserDto;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserDtoMapper;
import by.zelezinsky.reservationsystembooking.entity.user.Role;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.BadRequestException;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.RoleRepository;
import by.zelezinsky.reservationsystembooking.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final UserDtoMapper userDtoMapper;
    private final RoleRepository roleRepository;

    @Override
    public UserDto create(UserDto dto) {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("User with that username already exists");
        }
        Role role = findRole(dto.getRoleId());
        User entity = userDtoMapper.toEntity(dto);
        entity.setRole(role);
        return userDtoMapper.toDto(userRepository.save(entity));
    }

    @Override
    public UserDto update(UUID id, UserDto dto) {
        User user = findUser(id);
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new BadRequestException("User with that username already exists");
        }
        Role role = findRole(dto.getRoleId());
        user = userDtoMapper.toEntity(user, dto);
        user.setRole(role);
        return userDtoMapper.toDto(user);
    }

    @Override
    public UserDto findById(UUID id) {
        return userDtoMapper.toDto(findUser(id));
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userDtoMapper::toDto);
    }

    @Override
    public void delete(UUID id) {
        User user = findUser(id);
        userRepository.delete(user);
    }

    private Role findRole(UUID id) {
        return roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role", id.toString()));
    }

    private User findUser(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id.toString()));
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> byUsername = userRepository.findByUsername(username);
        if (byUsername.isEmpty()) {
            throw new NotFoundException(String.format("User with username %s not found", username));
        }
        User entity = userDtoMapper.toEntity(byUsername.get());
        return new org.springframework.security.core.userdetails.User(username, entity.getPassword(), true, true, true,
                true, entity.getAuthorities());
    }
}
