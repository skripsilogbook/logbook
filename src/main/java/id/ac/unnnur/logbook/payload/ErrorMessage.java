package id.ac.unnnur.logbook.payload;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class ErrorMessage implements Serializable {

    private String english;
    private String bahasa;
}
