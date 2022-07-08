package id.ac.unnnur.logbook.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class CommonResponse implements Serializable {
    @JsonProperty(value = "error_schema")
    private ErrorSchema errorSchema;

    @JsonProperty(value = "output_schema")
    private Object outputSchema;
}
