package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.GameProgress;
import dev.pronunciationAppBack.repository.GameProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameProgressService {

    @Autowired
    private GameProgressRepository gameProgressRepository;

    public GameProgress createGameProgress(GameProgress gameProgress) {
        return gameProgressRepository.save(gameProgress);
    }

    public Optional<GameProgress> getGameProgressById(Long id) {
        return gameProgressRepository.findById(id);
    }

    public List<GameProgress> getAllGameProgresses() {
        return gameProgressRepository.findAll();
    }

    public GameProgress updateGameProgress(Long id, GameProgress gameProgressDetails) {
        Optional<GameProgress> optionalGameProgress = gameProgressRepository.findById(id);
        if (optionalGameProgress.isPresent()) {
            GameProgress gameProgress = optionalGameProgress.get();
            gameProgress.setCurrentScore(gameProgressDetails.getCurrentScore());
            gameProgress.setCurrentStage(gameProgressDetails.getCurrentStage());
            gameProgress.setLastPlayedDate(gameProgressDetails.getLastPlayedDate());
            gameProgress.setWordsLearned(gameProgressDetails.getWordsLearned());
            gameProgress.setStatus(gameProgressDetails.getStatus());
            gameProgress.setUser(gameProgressDetails.getUser());
            gameProgress.setStage(gameProgressDetails.getStage());
            return gameProgressRepository.save(gameProgress);
        } else {
            return null;
        }
    }

    public void deleteGameProgress(Long id) {
        gameProgressRepository.deleteById(id);
    }
}
