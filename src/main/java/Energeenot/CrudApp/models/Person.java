package Energeenot.CrudApp.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {

    private int id;
    @NotEmpty(message = "Поле ФИО не может быть пустым")
    @Size(min = 2, max = 30, message = "В поле ФИО должно быть от двух до тридцати символов")
    private String fullName;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int yearOfBirth;

    public Person(int id, String fullName, int yearOfBirth) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public @NotEmpty(message = "Поле ФИО не может быть пустым") @Size(min = 2, max = 30, message = "В поле ФИО должно быть от двух до тридцати символов") String getFullName() {
        return fullName;
    }

    public void setFullName(@NotEmpty(message = "Поле ФИО не может быть пустым") @Size(min = 2, max = 30, message = "В поле ФИО должно быть от двух до тридцати символов") String fullName) {
        this.fullName = fullName;
    }

    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(@Min(value = 1900, message = "Год рождения должен быть больше 1900") int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
