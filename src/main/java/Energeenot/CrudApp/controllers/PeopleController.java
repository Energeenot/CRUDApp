package Energeenot.CrudApp.controllers;

import Energeenot.CrudApp.dao.CarDAO;
import Energeenot.CrudApp.dao.PersonDAO;
import Energeenot.CrudApp.models.Car;
import Energeenot.CrudApp.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/people")
public class PeopleController {

    private final PersonDAO personDAO;
    private final CarDAO carDAO;

    @Autowired
    public PeopleController(PersonDAO personDAO, CarDAO carDAO) {
        this.personDAO = personDAO;
        this.carDAO = carDAO;
    }

    @GetMapping()
    public String showAllPeople(Model model) {
        model.addAttribute("people", personDAO.showAll());
        return "/people/showAll";
    }

    @GetMapping("/{id}")
    public String showPerson(@PathVariable("id") int id, Model model) {
        Optional<Person> person = personDAO.show(id);
        person.ifPresent(value -> model.addAttribute("person", value));

        List<Car> cars = carDAO.findByPersonId(id);
        model.addAttribute("cars", cars);
        return "/people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping
    public String savePerson(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/people/new";
        }
        personDAO.create(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String editPerson(Model model, @PathVariable("id") int id) {
        Optional<Person> person = personDAO.show(id);
        person.ifPresent(value -> model.addAttribute("person", value));
        return "/people/edit";
    }

    @PatchMapping("/{id}")
    public String updatePerson(@ModelAttribute("person") @Valid Person person,BindingResult bindingResult,
                             @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/people/edit";
        }
        personDAO.update(id,person);
        return "redirect:/people";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable("id") int id) {
        personDAO.delete(id);
        return "redirect:/people";
    }
}
