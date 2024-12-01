package com.assessment.controller;

import com.assessment.model.Grocery;
import com.assessment.service.GroceryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/groceries")
public class AdminController {
    @Autowired
    private GroceryService service;

    @PostMapping
    public Grocery addItem(@RequestBody Grocery item) {
        return service.addItem(item);
    }

    @GetMapping
    public List<Grocery> getItems(@RequestParam(required = false) String category) {
        return service.getItems(category);
    }

    @DeleteMapping("/{id}")
    public String deleteItem(@PathVariable Long id) {
        service.deleteItem(id);
        return "Item deleted successfully";
    }

    @PutMapping("/{id}")
    public Grocery updateItem(@PathVariable Long id, @RequestBody Grocery item) {
        return service.updateItem(id, item);
    }

    @PatchMapping("/{id}/inventory")
    public Grocery updateInventory(@PathVariable Long id, @RequestBody int quantity) {
        return service.updateInventory(id, quantity);
    }
}
