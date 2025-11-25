package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Dish {
    private Long id;
    private String dishId;
    private String name;
    private String cuisine;
    private int preparationTime;

    private static Long counter = 6L;

    public Dish(String dishId, String name, String cuisine, int preparationTime) {
        this.id = counter++;
        this.dishId = dishId;
        this.name = name;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
    }
}
