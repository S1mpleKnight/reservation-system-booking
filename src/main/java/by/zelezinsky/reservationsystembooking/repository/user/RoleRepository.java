package by.zelezinsky.reservationsystembooking.repository.user;

import by.zelezinsky.reservationsystembooking.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, UUID> {

    Boolean existsByName(String name);
}
