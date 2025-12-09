package mk.ukim.finki.wp.lab.imp;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class DishServiceImp1 implements DishService {
    public DishRepository dishRepository;

    @Override
    public List<Dish> listDishes() {
        return dishRepository.findAll();
    }

    @Override
    public Dish findByDishId(String dishId) {
        return dishRepository.findByDishId(dishId);
    }

    public Dish findById(Long id) {
        return dishRepository.findById(id).orElse(null);
    }

    public Dish create(String dishId, String name, String cuisine, int preparationTime) {
        // ensure unique dishId
        Dish exists = dishRepository.findByDishId(dishId);
        if (exists != null) return null;
        Dish d = new Dish(dishId, name, cuisine, preparationTime);
        return dishRepository.save(d);
    }

    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish toUpdate = findById(id);
        if (toUpdate == null) return null;
        // check if new dishId collides with another dish
        Dish byDishId = dishRepository.findByDishId(dishId);
        if (byDishId != null && !Objects.equals(byDishId.getId(), id)) {
            return null;
        }
        toUpdate.setDishId(dishId);
        toUpdate.setName(name);
        toUpdate.setCuisine(cuisine);
        toUpdate.setPreparationTime(preparationTime);
        return dishRepository.save(toUpdate);
    }

    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

}
