package softlab.satestodavalebaback.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StudentSearchParams {

    private String name;
    private String lastName;
    private String idNumber;
    private Date birthDate;
}

