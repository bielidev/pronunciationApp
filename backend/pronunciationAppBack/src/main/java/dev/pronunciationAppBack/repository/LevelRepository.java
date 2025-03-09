package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.Level;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelRepository extends JpaRepository<Level, Long> {
    // Add custom query methods if required
}