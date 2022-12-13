package app;

import app.data.Beverage;
import app.data.BeverageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@SpringBootApplication
public class StartBeverageApplication {
    public static void main(String[] args)
    {
        SpringApplication.run(StartBeverageApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(BeverageRepository beverageRepository)
    {
        return args ->
        {
            beverageRepository.save(new Beverage("BrandA", "ProductA", 1L, false));
            beverageRepository.save(new Beverage("BrandB", "ProductB", Long.MAX_VALUE, false));
            beverageRepository.save(new Beverage("BrandC", "ProductC", 727L, true));
        };
    }
}
