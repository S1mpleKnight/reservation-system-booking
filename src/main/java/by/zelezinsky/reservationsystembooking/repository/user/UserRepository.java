package by.zelezinsky.reservationsystembooking.repository.user;

import by.zelezinsky.reservationsystembooking.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Boolean existsByUsername(String username);
}