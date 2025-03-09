package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    // Add custom query methods if required
}