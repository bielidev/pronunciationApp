package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Level;
import dev.pronunciationAppBack.repository.LevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LevelService {

    @Autowired
    private LevelRepository levelRepository;

    public Level createLevel(Level level) {
        return levelRepository.save(level);
    }

    public Optional<Level> getLevelById(Long id) {
        return levelRepository.findById(id);
    }

    public List<Level> getAllLevels() {
        return levelRepository.findAll();
    }

    public Level updateLevel(Long id, Level levelDetails) {
        Optional<Level> optionalLevel = levelRepository.findById(id);
        if (optionalLevel.isPresent()) {
            Level level = optionalLevel.get();
            level.setNumber(levelDetails.getNumber());
            level.setName(levelDetails.getName());
            level.setRequiredScore(levelDetails.getRequiredScore());
            level.setBlocked(levelDetails.isBlocked());
            level.setWords(levelDetails.getWords());
            return levelRepository.save(level);
        } else {
            return null;
        }
    }

    public void deleteLevel(Long id) {
        levelRepository.deleteById(id);
    }
}
