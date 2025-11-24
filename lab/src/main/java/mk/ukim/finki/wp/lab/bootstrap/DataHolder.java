package mk.ukim.finki.wp.lab.bootstrap;

import jakarta.annotation.PostConstruct;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataHolder {

    public static List<Chef> chefs = new ArrayList<>();
    public static List<Dish> dishes = new ArrayList<>();

    @PostConstruct
    public void init() {
        chefs.add(new Chef(1L, "name1", "lastName1", "blabla", null));
        chefs.add(new Chef(2L, "name2", "lastName2", "blabla", null));
        chefs.add(new Chef(3L, "name3", "lastName3", "blabla", null));
        chefs.add(new Chef(3L, "name4", "lastName4", "blabla", null));
        chefs.add(new Chef(3L, "name5", "lastName5", "blabla", null));

        dishes.add(new Dish("dish1", "dish1", "cuisine", 10));
        dishes.add(new Dish("dish2", "dish2", "cuisine", 10));
        dishes.add(new Dish("dish3", "dish3", "cuisine", 10));
        dishes.add(new Dish("dish4", "dish4", "cuisine", 10));
        dishes.add(new Dish("dish5", "dish5", "cuisine", 10));

    }
}
