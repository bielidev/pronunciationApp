package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.GameProgress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameProgressRepository extends JpaRepository<GameProgress, Long> {
    // Add custom query methods if required
}