package by.zelezinsky.reservationsystembooking.entity.user;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Table(name = "role")
@Entity
@Data
public class Role {

    @Id
    @Column(insertable = false, updatable = false, name = "id")
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;
}
