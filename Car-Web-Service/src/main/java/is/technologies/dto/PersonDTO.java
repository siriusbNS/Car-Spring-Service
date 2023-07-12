package is.technologies.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import java.sql.Date;

@Getter
@Setter
public class PersonDTO {
    private String username;

    private Date birth_date;

    private String password;
}
