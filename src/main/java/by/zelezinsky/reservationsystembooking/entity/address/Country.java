package by.zelezinsky.reservationsystembooking.entity.address;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Table(name = "country")
@Data
@Entity
public class Country {

    @Id
    @Column(nullable = false, name = "id", insertable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;
}
