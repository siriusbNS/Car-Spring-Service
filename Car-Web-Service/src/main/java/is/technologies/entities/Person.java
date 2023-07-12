package is.technologies.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Date;

@Data
@Getter
@Setter
@ToString
@Entity
@Table(schema = "public",name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "username")
    private String username;
    @Column(name = "birth_date")
    private Date birth_date;
    @Column(name = "password")
    private String password;
    @Column(name = "role")
    private String role;

    @OneToOne
    @JoinColumn(name = "brandid_",referencedColumnName = "brandid")
    private carBrand carBrand;
    public Person(String username,Date birth_date)
    {
        this.username= username;
        this.birth_date = birth_date;

    }
    public Person()
    {

    }
}
