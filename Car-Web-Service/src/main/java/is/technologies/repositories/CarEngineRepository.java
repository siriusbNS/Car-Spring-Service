package is.technologies.repositories;

import is.technologies.entities.carEngine;
import is.technologies.entities.carModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.Locale;


@Repository
public interface CarEngineRepository extends JpaRepository<carEngine, Integer> {
    List<carEngine> getAllByCarModelId(Integer carModel_id);
    List<carEngine> getAllByEngineName(String name);
    List<carEngine> getAllByEngineV(int engineV);
    List<carEngine> getAllByCountOfCylinders(int countOfCylinders);
    List<carEngine> getAllByEngineHeight(int height);
    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update carEngine set engineName=:name_ , engineV = :engineV_,countOfCylinders=:count,engineHeight=:height where id= :id_")
    void update(@Param("name_") String enginename, @Param("engineV_") int engineV,@Param("count") int count,@Param("height") int height, @Param("id_") Integer id);

}
