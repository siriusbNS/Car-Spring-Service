package is.technologies.entities;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;

@Setter
@Getter
@Data
@Entity
@ApiModel(value = "Car Brand",description = "Object of any Brand of car,contains name and other values")
@Table(name = "carbrand",schema = "public")
public class carBrand {
    public carBrand(Integer id, String name, Date foundingDate) {
        this.id = id;
        this.name = name;
        this.foundingDate = foundingDate;
    }
    public carBrand(String _name,Date _foundingDate)
    {
        if(_name == null || _foundingDate == null)
            throw new NullPointerException();

        name = _name;
        foundingDate = _foundingDate;
    }
    public carBrand() {

    }

    @Id
    @Column(name = "brandid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "brandname")
    private String name;
    @Column(name = "foundingdate")
    private Date foundingDate;



}
