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
@Table(name = "projects")
public class Project extends UserDateAudit {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column(name = "project_name")
    private String projectName;

    @Enumerated(EnumType.STRING)
    @Column(name = "project_type")
    private Type projectType;

    @Column(name = "project_status")
    private Boolean projectStatus;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinTable(name = "project_clients",
            joinColumns = { @JoinColumn(name = "project_id") },
            inverseJoinColumns = { @JoinColumn(name = "client_id") })
    private Set<Client> clients = new HashSet<>();
}
