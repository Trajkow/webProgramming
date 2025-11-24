package mk.ukim.finki.wp.lab.imp;

import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.repository.ChefRepository;
import mk.ukim.finki.wp.lab.repository.DishRepository;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Service;

import java.lang.reflect.Constructor;
import java.util.List;

@AllArgsConstructor
@Service
public class ChefServiceImp1 implements ChefService {

    public ChefRepository chefRepository;
    public DishRepository dishRepository;


    @Override
    public List<Chef> listChefs() {
        return chefRepository.findAll();
    }

    @Override
    public Chef findById(Long id) {
        return chefRepository.findById(id).get();
    }

    @Override
    public Chef addDishToChef(Long chefId, String dishId) {

        Dish dish = dishRepository.findByDishId(dishId);
        Chef chef = chefRepository.findById(chefId).get();

        chef.getDishes().add(dish);

        return chefRepository.save(chef);
    }
}
