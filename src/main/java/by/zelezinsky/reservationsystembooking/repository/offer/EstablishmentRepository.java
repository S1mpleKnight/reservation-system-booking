package by.zelezinsky.reservationsystembooking.repository.offer;

import by.zelezinsky.reservationsystembooking.entity.offer.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EstablishmentRepository extends JpaRepository<Establishment, UUID> {
}
