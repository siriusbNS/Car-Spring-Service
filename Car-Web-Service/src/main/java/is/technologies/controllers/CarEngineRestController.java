package is.technologies.controllers;

import io.swagger.annotations.Api;
import is.technologies.entities.Person;
import is.technologies.entities.carBrand;
import is.technologies.entities.carEngine;
import is.technologies.entities.carModel;
import is.technologies.repositories.CarEngineRepository;
import is.technologies.repositories.CarModelRepository;
import is.technologies.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/api/EnginesOfCars")
@Api(value = "Car Engines resources",description = "CRUD operations")
public class CarEngineRestController {
    private HttpServletRequest request;

    @Autowired
    public void setRequest(HttpServletRequest request1)
    {
        this.request = request1;
    }
    @GetMapping("/carEngines")
    public Collection<carEngine> allCarEngines()
    {
        if(request.isUserInRole("ADMIN"))
        {
            return this.carEngineRepository.findAll();
        }
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        ArrayList<carModel> carModels = (ArrayList<carModel>) carModelRepository.getAllByCarBrandId(person.getCarBrand().getId());
        ArrayList<carEngine> engines = new ArrayList<carEngine>();
        for (carModel i:carModels)
        {
           engines.addAll(carEngineRepository.getAllByCarModelId(i.getId()));
        }
            return engines;
    }
    @GetMapping("/carEngines/CarModel/{id}")
    public Collection<carEngine> allByCarModelId(@PathVariable Integer id)
    {
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        if(request.isUserInRole("ADMIN") || person.getCarBrand().getId() == id)
        {
            return this.carEngineRepository.getAllByCarModelId(id);
        }
       return null;
    }
    @PostMapping("/carEngines")
    public carEngine newOne(@RequestBody carEngine carEngine)
    {
        if(request.isUserInRole("ADMIN"))
        {
            return this.carEngineRepository.save(carEngine);
        }
        Person person = peopleRepository.findByUsername(request.getUserPrincipal().getName()).get();
        ArrayList<carModel> carModels = (ArrayList<carModel>) carModelRepository.getAllByCarBrandId(person.getCarBrand().getId());
        List<Integer> ids =  carModels.stream().map(x-> x.getId()).toList();
        if(ids.contains(carEngine.getCarModel().getId()))
        {
            return this.carEngineRepository.save(carEngine);
        }
        return null;

    }
    @GetMapping("/carEngines/{id}")
    public carEngine findOne(@PathVariable Integer id) throws Exception {
        if(request.isUserInRole("ADMIN"))
            return this.carEngineRepository.findById(id)
                .orElseThrow(() -> new Exception(id.toString()));
        return null;
    }
    @PutMapping("/carEngines/{id}")
    public void update(@PathVariable Integer id,@RequestBody carEngine carEngine) throws Exception {
        if(request.isUserInRole("ADMIN"))
            this.carEngineRepository.update(carEngine.getEngineName(),carEngine.getEngineV(),carEngine.getCountOfCylinders(),carEngine.getEngineHeight(),id);
    }
    @DeleteMapping("/carEngines/{id}")
    public void delete(@PathVariable Integer id) throws Exception {
        if(request.isUserInRole("ADMIN"))
            this.carEngineRepository.deleteById(id);
    }
    @GetMapping("/carEngines/Name/{name}")
    public Collection<carEngine> allByEngineName(@PathVariable String name)
    {
        if(request.isUserInRole("ADMIN"))
            return this.carEngineRepository.getAllByEngineName(name);
        return null;
    }
    @GetMapping("/carEngines/EngineV/{engineV}")
    public Collection<carEngine> allByEngineV(@PathVariable int engineV)
    {
        if(request.isUserInRole("ADMIN"))
            return this.carEngineRepository.getAllByEngineV(engineV);
        return null;
    }
    @GetMapping("/carEnginesall/CountOfCylinders/{count}")
    public Collection<carEngine> allByCountOfCylinders(@PathVariable int count)
    {
        if(request.isUserInRole("ADMIN"))
            return this.carEngineRepository.getAllByCountOfCylinders(count);
        return null;
    }
    @GetMapping("/carEngines/EngineHeight/{height}")
    public Collection<carEngine> allByEngineHeight(@PathVariable int height)
    {
        if(request.isUserInRole("ADMIN"))
            return this.carEngineRepository.getAllByEngineHeight(height);
        return null;
    }

    @Autowired
    private CarEngineRepository carEngineRepository;
    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private PeopleRepository peopleRepository;
}
