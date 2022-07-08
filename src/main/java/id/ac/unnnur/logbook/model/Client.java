package id.ac.unnnur.logbook.model;

import id.ac.unnnur.logbook.model.audit.UserDateAudit;
import id.ac.unnnur.logbook.util.Type;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clients")
public class Client extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long clientId;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "client_address")
    private String clientAddress;

    @Column(name = "client_note")
    private String clientNote;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private Type clientType;

    @Column(name = "client_status")
    private Boolean clientStatus;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "clients")
    private Set<Project> projects = new HashSet<>();
}
