package com.assessment.service;

import com.assessment.model.Grocery;
import com.assessment.model.Order;
import com.assessment.model.OrderItem;
import com.assessment.repository.GroceryRepository;
import com.assessment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private GroceryRepository groceryItemRepository;

    public Order createOrder(String userName, List<OrderItem> orderItems) {
        double totalAmount = 0;

        for (OrderItem orderItem : orderItems) {
            Grocery groceryItem = groceryItemRepository.findById(orderItem.getGroceryId())
                    .orElseThrow(() -> new RuntimeException("Item not found"));
            if (groceryItem.getQuantity() < orderItem.getQuantity()) {
                throw new RuntimeException("Insufficient stock for item: " + groceryItem.getName());
            }
            groceryItem.setQuantity(groceryItem.getQuantity() - orderItem.getQuantity());
            groceryItemRepository.save(groceryItem);

            orderItem.setPrice(groceryItem.getPrice());
            totalAmount += groceryItem.getPrice() * orderItem.getQuantity();
        }

        Order order = new Order();
        order.setUserName(userName);
        order.setItems(orderItems);
        order.setTotalAmount(totalAmount);

        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}