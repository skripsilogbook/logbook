package id.ac.unnnur.logbook.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
public class ProjectRequest implements Serializable {
    @NotBlank
    private String projectName;
}
