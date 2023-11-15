package com.example.controller;

import com.example.entity.Client;
import com.example.service.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class MarketController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/market")
    public String getMarket(Model model, HttpSession session) {
        model.addAttribute("books", session.getAttribute("books"));
        model.addAttribute("machines", session.getAttribute("machines"));
        model.addAttribute("telephones", session.getAttribute("telephones"));
        return "market";
    }

    @GetMapping("/")
    public String getLogin() {
        return "login";
    }

    @PostMapping("/login")
    public String isLogin(@RequestParam String login, @RequestParam String password, HttpSession session) throws IOException {
        Client client = clientService.getClientByLogin(login);
        if (client != null) {
            if (password.equals(client.getPassword())) {
                session.setAttribute("login", login);
                session.setAttribute("books", productService.getProducts(0, "book"));
                session.setAttribute("machines", productService.getProducts(2, "wash"));
                session.setAttribute("telephones", productService.getProducts(1, "telephone"));
                return "redirect:/market";
            }
        }
        return "redirect:/";
    }

    @PostMapping("/addToOrder")
    public String addToOrder(@RequestParam String type, @RequestParam String name, HttpSession session) {
        orderService.addToOrder(type, name, (String) session.getAttribute("login"));
        return "redirect:/market";
    }

    @GetMapping("/order")
    public String getOrderPage(Model model, HttpSession session) throws IOException {;
        model.addAttribute("products", orderService.getProducts((String) session.getAttribute("login")));
        return "order";
    }

    @PostMapping("/deleteFromOrder")
    public String deleteFromOrder(@RequestParam String type, @RequestParam String name, HttpSession session) {
        orderService.deleteFromOrder(type, name, (String) session.getAttribute("login"));
        return "redirect:/order";
    }

    @PostMapping("/reg")
    public String registration(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String login,
                               @RequestParam String password,
                               @RequestParam String email) {
        Client client = new Client(name, surname, email, login, password);
        return "redirect:/";
    }

    @GetMapping("/doOrder")
    public String doOrder(HttpSession session) throws IOException {
        orderService.doOrder((String) session.getAttribute("login"));
        session.setAttribute("books", productService.getProducts(0, "book"));
        session.setAttribute("machines", productService.getProducts(2, "wash"));
        session.setAttribute("telephones", productService.getProducts(1, "telephone"));
        return "redirect:/order";
    }

}
