package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;
import java.util.List;

@AllArgsConstructor
@WebServlet(name = "dish", urlPatterns = "/dish")
public class DishServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;

    private final DishService dishService;
    private final ChefService chefService;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String selectedChefId = req.getParameter("id");
        String selectedDishId = req.getParameter("dishId");

        if (selectedChefId == null || selectedDishId == null) {
            Long id = Long.parseLong(selectedChefId);

            chefService.addDishToChef(id, selectedDishId);
        }

        resp.sendRedirect("/chefs");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(req, resp);

        String selectedChefId = req.getParameter("id");

        if (selectedChefId == null) {
            resp.sendRedirect("/chefs");
            return;
        }

        Long id = Long.parseLong(selectedChefId);
        Chef selectedChef = chefService.findById(id);

        List<Dish> dishes = dishService.listDishes();


        WebContext context = new WebContext(exchange);
        context.setVariable("chef", selectedChef);
        context.setVariable("dishes", dishes);


        templateEngine.process("dishesList.html", context, resp.getWriter());
    }
}
