package mk.ukim.finki.wp.lab.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/dishes")
@RequiredArgsConstructor
public class DishController {

    private final DishService dishService;

    // 4.1 List dishes
    @GetMapping
    public String getDishesPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("dishes", dishService.listDishes());
        return "listDishes";
    }

    // 7. Add form page
    @GetMapping("/dish-form")
    public String getAddDishPage(Model model) {
        model.addAttribute("dish", null);
        return "dish-form";
    }

    // 7. Edit form page
    @GetMapping("/dish-form/{id}")
    public String getEditDishForm(@PathVariable Long id, Model model) {
        Dish dish = dishService.findById(id);
        if (dish == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        model.addAttribute("dish", dish);
        return "dish-form";
    }

    // 4.2 Create
    @PostMapping("/add")
    public String saveDish(@RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        Dish created = dishService.create(dishId, name, cuisine, preparationTime);
        if (created == null) {
            return "redirect:/dishes?error=DishIdExists";
        }
        return "redirect:/dishes";
    }

    // 4.3 Update
    @PostMapping("/edit/{id}")
    public String editDish(@PathVariable Long id,
                           @RequestParam String dishId,
                           @RequestParam String name,
                           @RequestParam String cuisine,
                           @RequestParam int preparationTime) {
        Dish updated = dishService.update(id, dishId, name, cuisine, preparationTime);
        if (updated == null) {
            return "redirect:/dishes?error=DishNotFound";
        }
        return "redirect:/dishes";
    }

    // 4.4 Delete
    @GetMapping("/delete/{id}")
    public String deleteDish(@PathVariable Long id) {
        dishService.delete(id);
        return "redirect:/dishes";
    }
}
