package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    // Add custom query methods if required
}