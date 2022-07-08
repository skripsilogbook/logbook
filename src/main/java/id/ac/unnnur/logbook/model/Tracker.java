package id.ac.unnnur.logbook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.ac.unnnur.logbook.model.audit.UserDateAudit;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "trackers")
public class Tracker extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trackerId;

    @Column(name = "tracker_activity")
    private String trackerActivity;

    @Column(name = "tracker_date")
    private LocalDate trackerDate;

    @Column(name = "start_hour")
    private LocalTime startHour;

    @Column(name = "end_hour")
    private LocalTime endHour;

    @Column(name = "tracker_status")
    private Boolean trackerStatus;

    @Column(name = "tracker_by")
    private String trackerBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Project project;
}
