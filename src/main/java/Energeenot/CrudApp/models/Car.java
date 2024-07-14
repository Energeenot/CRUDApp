package Energeenot.CrudApp.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Car {

    private int id;
    //класс-обертка Integer, чтобы допускать null
    private Integer personId;

    @NotEmpty(message = "")
    @Size(min = 2, max = 30)
    private String manufacturer;

    @Size(min = 2, max = 30)
    @NotEmpty(message = "")
    private String model;


    @Min(value = 1900, message = "Год производства должен быть больше 1900")
    private int yearOfProduction;



    public Car (int id, String manufacturer, String model, int yearOfProduction, Integer personId) {
        this.id = id;
        this.manufacturer = manufacturer;
        this.model = model;
        this.yearOfProduction = yearOfProduction;
        this.personId = personId;
    }

    public Car(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "") @Size(min = 2, max = 30) String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(@NotEmpty(message = "") @Size(min = 2, max = 30) String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public @Size(min = 2, max = 30) @NotEmpty(message = "") String getModel() {
        return model;
    }

    public void setModel(@Size(min = 2, max = 30) @NotEmpty(message = "") String model) {
        this.model = model;
    }


    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    @Min(value = 1900, message = "Год производства должен быть моложе 1900")
    public int getYearOfProduction() {
        return yearOfProduction;
    }

    public void setYearOfProduction(@Min(value = 1900, message = "Год производства должен быть моложе 1900") int yearOfProduction) {
        this.yearOfProduction = yearOfProduction;
    }
}
