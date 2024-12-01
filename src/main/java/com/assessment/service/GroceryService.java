package com.assessment.service;

import com.assessment.model.Grocery;
import com.assessment.repository.GroceryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroceryService {
    @Autowired
    private GroceryRepository repository;

    public Grocery addItem(Grocery item) {
        return repository.save(item);
    }

    public List<Grocery> getItems(String category) {
        return category == null ? repository.findAll() : repository.findByCategory(category);
    }

    public void deleteItem(Long id) {
        repository.deleteById(id);
    }

    public Grocery updateItem(Long id, Grocery updatedItem) {
        Grocery item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        if (updatedItem.getName() != null) item.setName(updatedItem.getName());
        if (updatedItem.getPrice() > 0) item.setPrice(updatedItem.getPrice());
        if (updatedItem.getQuantity() >= 0) item.setQuantity(updatedItem.getQuantity());
        return repository.save(item);
    }

    public Grocery updateInventory(Long id, int quantity) {
        Grocery item = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        item.setQuantity(quantity);
        return repository.save(item);
    }
}
