package id.ac.unnnur.logbook.payload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ErrorSchema implements Serializable {
    @JsonProperty(value = "error_code")
    private String errorCode;

    @JsonProperty(value = "error_message")
    private ErrorMessage errorMessage;
}
