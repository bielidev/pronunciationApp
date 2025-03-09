package dev.pronunciationAppBack.repository;

import dev.pronunciationAppBack.model.StageWords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StageWordsRepository extends JpaRepository<StageWords, Long> {
    // Add custom query methods if required
}