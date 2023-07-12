package is.technologies.controllers;

import io.swagger.annotations.Api;
import is.technologies.entities.*;
import is.technologies.repositories.CarModelRepository;
import is.technologies.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Objects;

@RestController
@RequestMapping("/api/ModelsOfCars")
@Api(value = "Car Models resources",description = "CRUD operations")
public class CarModelRestController {
    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request1)
    {
        this.request = request1;
    }

    @GetMapping("/carModels")
    public Collection<carModel> allCarModels()
    {
        if(request.isUserInRole("ADMIN"))
        {
            return this.carModelRepository.findAll();
        }
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        return this.carModelRepository.getAllByCarBrandId(person.getCarBrand().getId());
    }
    @GetMapping("/carModels/carBrand/{id}")
    public Collection<carModel> allAllByCarBrandId(@PathVariable Integer id)
    {
        if(request.isUserInRole("ADMIN"))
        {
            return this.carModelRepository.getAllByCarBrandId(id);
        }

         Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        if(Objects.equals(id, person.getCarBrand().getId()))
            return this.carModelRepository.getAllByCarBrandId(person.getCarBrand().getId());
        return null;


    }
    @GetMapping("/carModels/Name/{name}")
    public Collection<carModel> allByModelName(@PathVariable String name)
    {
        if(request.isUserInRole("ADMIN"))
        {
            return this.carModelRepository.getAllByModelName(name);
        }
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        return this.carModelRepository.getAllByModelNameAndCarBrandId(name,person.getCarBrand().getId());
    }
    @GetMapping("/carModels/CarBodyType/{carBodyType}")
    public Collection<carModel> allByModelBodyType( @PathVariable String carBodyType)
    {
        if(request.isUserInRole("ADMIN"))
        return this.carModelRepository.getAllByCarBodyType(carBodyType);

         Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        return this.carModelRepository.getAllByCarBodyTypeAndCarBrandId(carBodyType,person.getCarBrand().getId());
    }
    @PostMapping("/carModels")
    public carModel newOne(@RequestBody carModel carModel) throws Exception {
        if(request.isUserInRole("ADMIN"))
        {
            return this.carModelRepository.save(carModel);
        }
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        if(Objects.equals(carModel.getCarBrand().getId(), person.getCarBrand().getId()))
        {
            return this.carModelRepository.save(carModel);
        }
        return null;
    }
    @GetMapping("/carModels/{id}")
    public carModel findOne(@PathVariable Integer id) throws Exception {
        if (request.isUserInRole("ADMIN"))
        {
            return this.carModelRepository.findById(id)
                    .orElseThrow(() -> new Exception(id.toString()));

        }

        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        return this.carModelRepository.getFirstByIdAndCarBrandId(id,person.getCarBrand().getId());
    }
    @PutMapping("/carModels/{id}")
    public void update(@PathVariable Integer id,@RequestBody   carModel carModel) throws Exception {
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        if((person.getCarBrand().getId() == carModel.getCarBrand().getId()) || request.isUserInRole("ADMIN"))
        this.carModelRepository.update(carModel.getModelName(),carModel.getLength(),carModel.getWeidth(),carModel.getCarBodyType(),carModel.getHeight(),id);
    }
    @DeleteMapping("/carModels/{id}")
    public void delete( @PathVariable Integer id) throws Exception {
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        if((person.getCarBrand().getId() == id) || request.isUserInRole("ADMIN"))
        this.carModelRepository.deleteById(id);
    }
    @GetMapping("/carModels/carBrand")
    public Collection<carModel> allByCarBrand(@RequestBody carBrand carBrand)
    {
        if(request.isUserInRole("ADMIN"))
        return this.carModelRepository.getAllByCarBrand(carBrand);
        return null;
    }
    @GetMapping("/carModels/length/{length}")
    public Collection<carModel> allByLength(int length)
    {
        if(request.isUserInRole("ADMIN"))
        {
            return this.carModelRepository.getAllByLength(length);
        }
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        return this.carModelRepository.getAllByLengthAndCarBrandId(length,person.getCarBrand().getId());


    }


    @Autowired
    private CarModelRepository carModelRepository;
    @Autowired
    private PeopleRepository peopleRepository;
}
