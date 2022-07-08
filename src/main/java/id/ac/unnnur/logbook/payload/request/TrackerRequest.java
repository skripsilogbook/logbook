package id.ac.unnnur.logbook.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
public class TrackerRequest implements Serializable {
    @NotBlank
    private String trackerActivity;

    @NotNull
    private LocalDate trackerDate;

    @NotNull
    private LocalTime startHour;

    @NotBlank
    private String trackerBy;

    private LocalTime endHour;
    private Long projectId;
}
