package id.ac.unnnur.logbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.unnnur.logbook.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tasks")
public class Task extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_status")
    private Boolean taskStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User taskAssignee;
}
