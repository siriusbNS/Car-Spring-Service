package is.technologies.repositories;

import io.swagger.models.auth.In;
import is.technologies.entities.carBodyType;
import is.technologies.entities.carBrand;
import is.technologies.entities.carModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
@Repository
public interface CarModelRepository extends JpaRepository<carModel,Integer> {
    List<carModel> getAllByCarBrandId(Integer id);
    List<carModel> getAllByModelName(String name);
    List<carModel> getAllByModelNameAndCarBrandId(String name,Integer id);
    List<carModel> getAllByCarBrand(carBrand carBrand);
    List<carModel> getAllByCarBodyType(String carBodyType);
    List<carModel> getAllByCarBodyTypeAndCarBrandId(String carBodyType,Integer id);
    List<carModel> getAllByLength(int lenght);
    List<carModel> getAllByLengthAndCarBrandId(int lenght,Integer id);
    List<carModel> getAllByIdAndAndCarBrandId(Integer id1,Integer id2);
     carModel getFirstByIdAndCarBrandId(Integer id1, Integer id2);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update carModel set modelName=:name_ , length = :length_, weidth=:weidth_,carBodyType=:btype,height =:height_ where id =:id_")
    void update(@Param("name_") String modelname, @Param("length_") int modellenght, @Param("weidth_") int weidth,@Param("btype") String btype, @Param("height_") int height, @Param("id_") Integer id);

}
