package by.zelezinsky.reservationsystembooking.repository;

import by.zelezinsky.reservationsystembooking.entity.user.Permission;
import by.zelezinsky.reservationsystembooking.entity.user.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, UUID> {

    List<Permission> findAllByRole(Role role);
}
