package mk.ukim.finki.wp.lab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Dish {
    String dishId;
    String name;
    String cuisine;
    int preparationTime;
}
