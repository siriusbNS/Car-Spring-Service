package is.technologies.commandLR;

import is.technologies.entities.carBrand;
import is.technologies.entities.carModel;
import is.technologies.repositories.CarBrandRepository;
import is.technologies.repositories.CarModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;

@Component
public class carBrandCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        Integer id = 5;
        carBrand carBrand = new carBrand("Vlad",new Date(2000,12,12));
        carModel carModel = carBrandRepository.getFirstByIdAndCarBrandId(1,3);
        System.out.println(carModel.toString());
    }

    @Autowired
    CarModelRepository carBrandRepository;

}
