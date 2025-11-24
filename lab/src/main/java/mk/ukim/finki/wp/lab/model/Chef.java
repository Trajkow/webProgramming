package mk.ukim.finki.wp.lab.model;

import lombok.*;

import javax.management.ConstructorParameters;
import java.beans.ConstructorProperties;
import java.util.List;
@Setter
@Getter
@AllArgsConstructor
public class Chef {
    Long id;
    String firstName;
    String lastName;
    String bio;
    List<Dish> dishes;
}
