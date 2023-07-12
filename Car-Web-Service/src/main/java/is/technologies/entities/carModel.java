package is.technologies.entities;

import javax.persistence.*;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;


@Getter
@Setter
@ToString
@Data
@Entity
@ApiModel(value = "Car Model",description = "Object of any Model of car,contains name and other values")
@Table(name = "carmodel",schema = "public")
public class carModel {

    public carModel() {
    }
    @Id
    @Column(name = "modelid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "modelname")
    private String modelName;
    @Column(name = "bodytype")
    private String carBodyType;
    @Column(name = "modellength")
    private int length;
    @Column(name = "modelwidth")
    private int weidth;
    @Column(name = "modelheight")
    private int height;


    @ManyToOne
    @JoinColumn(name = "brandid_",referencedColumnName = "brandid")
    private carBrand carBrand;


    public String getCarBodyType() {
        return carBodyType;
    }
    public void setCarBodyType(String _carBodyType) {
        this.carBodyType = _carBodyType;
    }
    public void setCarBodyType(carBodyType _carBodyType) {
        this.carBodyType = _carBodyType.toString();
    }

    public carModel(int id, String modelName, String carBodyType, int length, int weidth, is.technologies.entities.carBrand carBrand,int height) {
        this.id = id;
        this.modelName = modelName;
        this.carBodyType = carBodyType;
        this.length = length;
        this.weidth = weidth;
        this.carBrand = carBrand;
        this.height = height;
    }

    public carModel(String _modelName, int _length, int _weidth, carBodyType _carBodyType, carBrand _carBrand)
    {
        if(_modelName == null || _length == 0 || _weidth == 0 || _carBodyType == null || _carBrand == null)
            throw new NullPointerException();

        modelName = _modelName;
        length = _length;
        weidth = _weidth;
        carBodyType = _carBodyType.toString();
        carBrand = _carBrand;

    }

}
