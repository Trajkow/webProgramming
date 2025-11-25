package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;


@AllArgsConstructor
@WebServlet(name = "chefDetails", urlPatterns = "/chefDetails")
public class ChefDetailsServlet extends HttpServlet {

    private final SpringTemplateEngine templateEngine;

    private final ChefService chefService;
    private final DishService dishService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(req.getServletContext())
                .buildExchange(req, resp);

        WebContext context = new WebContext(exchange);

        String chefId = req.getParameter("id");

        Chef chef = chefService.findById(Long.parseLong(chefId));

        context.setVariable("chef", chef);

        templateEngine.process("chefDetails", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String chefId = req.getParameter("id");
        String dishId = req.getParameter("dishId");




    }

}

