package mk.ukim.finki.wp.lab.imp;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return dishRepository.findById(id).isPresent() ? dishRepository.findById(id).get() : null;
    }

    public Dish create(String dishId, String name, String cuisine, int preparationTime) {

        Dish exists = dishRepository.findByDishId(dishId);

        if (exists == null) {
            dishRepository.save(new Dish(dishId, name, cuisine, preparationTime));
            return dishRepository.findByDishId(dishId);
        }
        return null;
    }

    public Dish update(Long id, String dishId, String name, String cuisine, int preparationTime) {
        Dish exists = dishRepository.findByDishId(dishId);

        if (exists == null) {
            return null;
        } else {
            dishRepository.save(new Dish(dishId, name, cuisine, preparationTime));
        }
        return exists;
    }

    public void delete(Long id) {
        dishRepository.deleteById(id);
    }

}
