package is.technologies.controllers;

import io.swagger.annotations.Api;
import is.technologies.entities.carBrand;
import is.technologies.repositories.CarBrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/BrandOfCars")
@Api(value = "Car Brands resources",description = "CRUD operations")
public class CarBrandRestController {
   @GetMapping("/carBrands")
    public Collection<carBrand> allCarBrands()
    {
        return this.carBrandRepository.findAll();
    }
    @PostMapping("/carBrands")
    public carBrand newCarBrand(@RequestBody carBrand carBrand)
    {
        return this.carBrandRepository.save(carBrand);
    }
    @GetMapping("/carBrands/{id}")
    public carBrand findCarBrand(@PathVariable Integer id) throws Exception {
        return this.carBrandRepository.findById(id).get();

    }
    @PutMapping("/carBrands/{id}")
    public void updateCarBrand(@PathVariable Integer id,@RequestBody carBrand carBrand) throws Exception {
         this.carBrandRepository.update(carBrand.getName(),carBrand.getFoundingDate(),id);
    }
    @DeleteMapping("/carBrands/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        this.carBrandRepository.deleteById(id);
    }
    @GetMapping("/carBrands/Name/{name}")
    public Collection<carBrand> allCarBrandsByName(@PathVariable String name)
    {

        return this.carBrandRepository.getAllByName(name);
    }
    @GetMapping("/carBrands/Date/{date}")
    public Collection<carBrand> allCarBrandsByFoundingDate(@PathVariable String date) throws ParseException {
        Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        java.sql.Date date2 = new java.sql.Date(date1.getYear(),date1.getMonth(),date1.getDay());
        System.out.println(date2);
        return this.carBrandRepository.getAllByFoundingDate(date2);
    }
    @Autowired
    private CarBrandRepository carBrandRepository;
}
