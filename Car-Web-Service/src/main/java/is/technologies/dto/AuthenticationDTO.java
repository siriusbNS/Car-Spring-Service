package is.technologies.dto;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import java.sql.Date;

@Getter
@Setter
public class AuthenticationDTO {
    private String username;
    private String password;
}
