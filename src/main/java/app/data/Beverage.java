package app.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Beverage {
    @Id
    @GeneratedValue
    private Long id;
    private String brand;
    private String name;
    private Long volumeInMilliliters;
    private Boolean isAlcoholic;

    public Beverage(String brand, String name, Long volumeInMilliliters, Boolean isAlcoholic) {
        this.brand = brand;
        this.name = name;
        this.volumeInMilliliters = volumeInMilliliters;
        this.isAlcoholic = isAlcoholic;
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "id=" + id +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", volumeInMililiters=" + volumeInMilliliters +
                ", isAlcoholic=" + isAlcoholic +
                '}';
    }
}
