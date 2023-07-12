package is.technologies.entities;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Data
@Entity
@ApiModel(value = "Car Engine",description = "Object of any Engine of car,contains name and other values")
@Table(name = "carengine",schema = "public")
public class carEngine {

    @Id
    @Column(name = "engineid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "enginename")
    private String engineName;
    @Column(name = "enginev")
    private int engineV;
    @Column(name = "countofcylinders")
    private int countOfCylinders;
    @Column(name = "engineheight")
    private int engineHeight;


    @ManyToOne
    @JoinColumn(name = "modelid_",referencedColumnName = "modelid")
    private carModel carModel;
    public carEngine()
    {

    }
    public carEngine(int id, String engineName, int engineV, int  countOfCylinders, int engineHeight, is.technologies.entities.carModel carModel) {
        this.id = id;
        this.engineName = engineName;
        this.engineV = engineV;
        this.countOfCylinders= countOfCylinders;
        this.engineHeight= engineHeight;
        this.carModel = carModel;
    }

}
