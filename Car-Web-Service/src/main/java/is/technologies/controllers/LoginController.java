package is.technologies.controllers;

import is.technologies.entities.Person;
import is.technologies.services.RegistrationService;
import is.technologies.util.PersonValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/here")
public class LoginController {
    private final PersonValidator personValidator;
    private final RegistrationService registrationService;

    @Autowired
    public LoginController(PersonValidator personValidator, RegistrationService registrationService) {
        this.personValidator = personValidator;
        this.registrationService = registrationService;

    }

    @GetMapping("/login")
    public String loginPage()
    {
        return "login";
    }

    @GetMapping("/registration")
    public String registraionPage(@ModelAttribute("person") Person person)
    {
        return "registration";
    }
    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("person") @Valid Person person,
                                      BindingResult bindingResult)
    {
        personValidator.validate(person,bindingResult );
        if (bindingResult.hasErrors())
            return "/registration";
        registrationService.register(person);

        return "redirect:/here/login";
    }

}
