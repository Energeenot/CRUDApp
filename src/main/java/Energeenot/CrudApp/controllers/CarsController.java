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

import java.util.Optional;

@Controller
@RequestMapping("/cars")
public class CarsController {

    private final CarDAO carDAO;
    private final PersonDAO personDAO;

    @Autowired
    public CarsController(CarDAO carDAO, PersonDAO personDAO) {
        this.carDAO = carDAO;
        this.personDAO = personDAO;
    }

    @GetMapping
    public String showAllCars(Model model) {
        model.addAttribute("cars", carDAO.showAll());
        return "/cars/showAll";
    }

    @GetMapping("/{id}")
    public String showCar(@PathVariable("id") int id, Model model, @ModelAttribute("person")Person person) {
        Optional<Car> car = carDAO.show(id);
        car.ifPresent(value -> model.addAttribute("car", value));
        model.addAttribute("people", personDAO.showAll());

        boolean isBelong = carDAO.isBelongToPerson(id);
        model.addAttribute("isBelong", isBelong);
        if (isBelong){
            int personId = carDAO.findWhoOwnsCar(id);
            Optional<Person> owner = personDAO.show(personId);
            owner.ifPresent(value -> model.addAttribute("owner", value));
        }

        return "/cars/show";
    }

    @PatchMapping("/{id}/choose")
    public String choosePerson(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        carDAO.choosePerson(person.getId(), id);
        return "redirect:/cars/" + id;
    }

    @PatchMapping("/{id}/liberate")
    public String liberateCar(@PathVariable("id") int id) {
        carDAO.liberateCar(id);
        return "redirect:/cars/" + id;
    }

    @GetMapping("/new")
    public String newCar(@ModelAttribute("car") Car car) {
        return "/cars/new";
    }

    @PostMapping
    public String saveCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/cars/new";
        }
        carDAO.create(car);
        return "redirect:/cars";
    }

    @GetMapping("/{id}/edit")
    public String editCar(Model model, @PathVariable("id") int id) {
        Optional<Car> car = carDAO.show(id);
        car.ifPresent(value -> model.addAttribute("car", value));
        return "/cars/edit";
    }


    @PatchMapping("/{id}")
    public String updateCar(@ModelAttribute("car") @Valid Car car, BindingResult bindingResult,
                            @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "/cars/edit";
        }
        carDAO.update(id, car);
        return "redirect:/cars";
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable int id) {
        carDAO.delete(id);
        return "redirect:/cars";
    }

}
