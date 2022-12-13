package app.rest;

import app.data.Beverage;
import app.data.BeverageRepository;
import app.error.BeverageNotFoundException;
import app.error.PatchException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class BeverageController {
    @Autowired
    private BeverageRepository repository;

    @GetMapping("/beverages")
    List<Beverage> findAll()
    {
        return repository.findAll();
    }

    @PostMapping("/beverages")
    @ResponseStatus(HttpStatus.CREATED)
    Beverage newBeverage(@RequestBody CreateBeverageRequest request)
    {
        Beverage beverage = new Beverage();
        beverage.setIsAlcoholic(request.getIsAlcoholic());
        beverage.setBrand(request.getBrand());
        beverage.setVolumeInMilliliters(request.getVolumeInMilliliters());
        beverage.setName(request.getName());

        return repository.save(beverage);
    }

    @GetMapping("/beverages/{id}")
    Beverage findOne(@PathVariable Long id)
    {
        return repository
            .findById(id)
            .orElseThrow(() -> new BeverageNotFoundException(id));
    }

    @PutMapping("/beverages/{id}")
    Beverage saveOrUpdate(@RequestBody Beverage newBeverage, @PathVariable Long id)
    {
        return repository.findById(id)
            .map(beverage -> {
                beverage.setId(id);
                beverage.setBrand(newBeverage.getBrand());
                beverage.setName(newBeverage.getName());
                beverage.setVolumeInMilliliters(newBeverage.getVolumeInMilliliters());
                beverage.setIsAlcoholic(newBeverage.getIsAlcoholic());

                return repository.save(beverage);
            })
            .orElseGet(() -> {
                newBeverage.setId(id);
                return repository.save(newBeverage);
            });
    }

    @PatchMapping("/beverages/{id}")
    Beverage patch(@RequestBody Map<String, String> update, @PathVariable Long id)
    {
        return repository.findById(id)
            .map(beverage -> {
                HashSet<String> fieldSet = new HashSet<>();
                fieldSet.add("brand");
                fieldSet.add("name");
                fieldSet.add("volumeInMilliliters");
                fieldSet.add("isAlcoholic");

                for (String field : update.keySet()) {
                    if (!fieldSet.contains(field)) {
                        throw new PatchException(field);
                    }
                }

                String brand = update.get("brand");
                if (!StringUtils.isEmpty(brand)) {
                    beverage.setBrand(brand);
                }

                String name = update.get("name");
                if (!StringUtils.isEmpty(name)) {
                    beverage.setName(name);
                }

                String volume = update.get("volumeInMililiters");
                if (!StringUtils.isEmpty(volume)) {
                    beverage.setVolumeInMilliliters(Long.getLong(volume));
                }

                String isAlcoholic = update.get("isAlcoholic");
                if (!StringUtils.isEmpty(isAlcoholic)) {
                    beverage.setIsAlcoholic(Boolean.valueOf(isAlcoholic));
                }

                return repository.save(beverage);
            })
            .orElseThrow(() -> new BeverageNotFoundException(id));
    }

    @DeleteMapping("/beverages/{id}")
    void deleteBeverage(@PathVariable Long id)
    {
        if (!repository.existsById(id)) {
            return;
        }

        repository.deleteById(id);
    }
}
