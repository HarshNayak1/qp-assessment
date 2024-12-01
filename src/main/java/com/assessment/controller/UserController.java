package com.assessment.controller;

import com.assessment.model.Grocery;
import com.assessment.model.Order;
import com.assessment.model.OrderItem;
import com.assessment.service.GroceryService;
import com.assessment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private GroceryService groceryService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/groceries")
    public List<Grocery> getItems(@RequestParam(required = false) String category) {
        return groceryService.getItems(category);
    }

    @PostMapping("/orders")
    public Order createOrder(@RequestParam String userName, @RequestBody List<OrderItem> orderItems) {
        return orderService.createOrder(userName, orderItems);
    }
}
