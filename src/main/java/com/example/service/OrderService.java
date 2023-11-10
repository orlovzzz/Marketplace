package com.example.service;

import com.example.DTO.ProdCount;
import com.example.entity.*;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ClientService clientService;
    @Autowired
    private BookService bookService;
    @Autowired
    private TelephoneService telephoneService;
    @Autowired
    private WashingMachineService washingMachineService;

    public void addToOrder(String type, String name, String login) {
        int id = -1;
        Client client = clientService.getClientByLogin(login);
        switch(type) {
            case ("Book"):
                id = bookService.getBookByName(name).getId();
                break;
            case("Electronic"):
                id = telephoneService.getTelephoneByName(name).getId();
                break;
            case("Plumbing"):
                id = washingMachineService.getWashingMachineByName(name).getId();
                break;
        }
        Order order;
        if (id != -1 && client != null) {
            order = orderRepository.findByProductIdAndClientIdAndType(id, client.getId(), type);
            if (order != null) {
                order.setCount(order.getCount() + 1);
            } else {
                order = new Order();
                order.setClient(client);
                order.setProductId(getProduct(type, name));
                order.setCount(1);
                order.setType(type);
            }
            orderRepository.save(order);
        }
    }

    public int getProduct(String type, String name) {
        switch(type) {
            case ("Book"):
                return bookService.getBookByName(name).getId();
            case("Electronic"):
                return telephoneService.getTelephoneByName(name).getId();
            case("Plumbing"):
                return washingMachineService.getWashingMachineByName(name).getId();
            default:
                return 0;
        }
    }

    public List<ProdCount> getProducts(String login) throws IOException {
        List<ProdCount> products = new ArrayList<>();
        List<Order> orders = orderRepository.findByClientId(clientService.getClientByLogin(login).getId());
        for (Order order : orders) {
            if (order.getType().equals("Book")) {
                products.add(new ProdCount(productService.getCurrentProduct(0, "/book/" + order.getProductId()), order.getCount()));
            }
            else if (order.getType().equals("Plumbing")) {
                products.add(new ProdCount(productService.getCurrentProduct(2, "/wash/" + order.getProductId()), order.getCount()));
            }
            else {
                products.add(new ProdCount(productService.getCurrentProduct(1, "/telephone/" + order.getProductId()), order.getCount()));
            }
        }
        return products;
    }

    public void deleteFromOrder(String type, String name, String login) {
        Order order = orderRepository.findByProductIdAndClientIdAndType(getProduct(type, name), clientService.getClientByLogin(login).getId(), type);
        if (order != null) {
            if (order.getCount() != 1) {
                order.setCount(order.getCount() - 1);
                orderRepository.save(order);
                return;
            }
            orderRepository.deleteById(order.getId());
        }
    }

    public void doOrder(String login) throws IOException{
        List<ProdCount> products = getProducts(login);
        int id = clientService.getClientByLogin(login).getId();
        for (ProdCount prod : products) {
            if (prod.getProducts() instanceof Book) {
                Book book = (Book) prod.getProducts();
                if (book.getNumber() >= prod.getCount()) {
                    book.setNumber(book.getNumber() - prod.getCount());
                    bookService.changeBook(book.getId(), book);
                    orderRepository.deleteById(orderRepository.findByProductIdAndClientIdAndType(book.getId(), id, book.getType()).getId());
                }
            } else if (prod.getProducts() instanceof Telephone) {
                Telephone phone = (Telephone) prod.getProducts();
                if (phone.getNumber() >= prod.getCount()) {
                    phone.setNumber(phone.getNumber() - prod.getCount());
                    telephoneService.changeTelephone(phone.getId(), phone);
                    orderRepository.deleteById(orderRepository.findByProductIdAndClientIdAndType(phone.getId(), id, phone.getType()).getId());
                }
            } else if (prod.getProducts() instanceof WashingMachine) {
                WashingMachine wash = (WashingMachine) prod.getProducts();
                if (wash.getNumber() >= prod.getCount()) {
                    wash.setNumber(wash.getNumber() - prod.getCount());
                    washingMachineService.changeWashingMachine(wash.getId(), wash);
                    orderRepository.deleteById(orderRepository.findByProductIdAndClientIdAndType(wash.getId(), id, wash.getType()).getId());
                }
            }
        }
    }

}