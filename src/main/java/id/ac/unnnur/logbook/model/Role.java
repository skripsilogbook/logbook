package id.ac.unnnur.logbook.model;

import id.ac.unnnur.logbook.model.audit.DateAudit;
import id.ac.unnnur.logbook.util.RoleType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
public class Role extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", nullable = false)
    private RoleType roleName;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "role")
    private User user;
}
