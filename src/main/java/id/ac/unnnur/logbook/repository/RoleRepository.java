package id.ac.unnnur.logbook.repository;

import id.ac.unnnur.logbook.model.Role;
import id.ac.unnnur.logbook.util.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRoleName(RoleType roleName);
}
