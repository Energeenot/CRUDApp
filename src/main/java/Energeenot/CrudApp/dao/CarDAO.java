package Energeenot.CrudApp.dao;

import Energeenot.CrudApp.models.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
public class CarDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CarDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> showAll() {
        return jdbcTemplate.query("SELECT * FROM Car", new BeanPropertyRowMapper<Car>(Car.class));
    }

    public Optional<Car> show(int id) {
        return jdbcTemplate.query("SELECT * FROM Car WHERE id=?",  new BeanPropertyRowMapper<>(Car.class), id).stream().findAny();
    }

    public void create(Car car) {
        jdbcTemplate.update("INSERT INTO Car(manufacturer, model, yearOfProduction) VALUES (?, ?, ?)", car.getManufacturer(), car.getModel(), car.getYearOfProduction());
    }

    public void update(int id, Car car) {
        jdbcTemplate.update("UPDATE Car SET manufacturer=?, model=?, yearOfProduction=? WHERE id=?", car.getManufacturer(), car.getModel(), car.getYearOfProduction(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Car WHERE id=?", id);
    }


    public List<Car> findByPersonId(int id) {
        return jdbcTemplate.query("SELECT * FROM Car WHERE personId=?", new BeanPropertyRowMapper<>(Car.class), id);
    }

    public void choosePerson(int personId, int carId) {
        jdbcTemplate.update("UPDATE Car SET personId=? where id=?", personId, carId);
    }

    public boolean isBelongToPerson(int carId) {
        Integer personId = jdbcTemplate.queryForObject("SELECT personId FROM Car WHERE id=?", Integer.class, carId);
        return personId != null;
    }

    public int findWhoOwnsCar(int carId) {
        return Objects.requireNonNull(jdbcTemplate.queryForObject("SELECT personId FROM Car WHERE id=?", Integer.class, carId));
    }

    public void liberateCar(int carId) {
        jdbcTemplate.update("UPDATE Car SET personId=null WHERE id=?", carId);
    }

}
