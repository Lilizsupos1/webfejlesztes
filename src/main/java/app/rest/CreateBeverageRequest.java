package app.rest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateBeverageRequest {
    private String brand;
    private String name;
    private Long volumeInMilliliters;
    private Boolean isAlcoholic;
}
