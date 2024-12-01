package com.assessment.repository;

import com.assessment.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroceryRepository extends JpaRepository<Grocery,Long> {

    List<Grocery> findByCategory(String category);

}
