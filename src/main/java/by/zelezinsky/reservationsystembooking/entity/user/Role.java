package by.zelezinsky.reservationsystembooking.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Table(name = "roles")
@Entity
@Data
@ToString
public class Role {

    @Id
    @Column(name = "id", nullable = false)
    private UUID id = UUID.randomUUID();

    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<User> users;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private Set<Permission> permissions;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role role)) return false;

        return getId() != null ? getId().equals(role.getId()) : role.getId() == null;
    }

    @Override
    public int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
