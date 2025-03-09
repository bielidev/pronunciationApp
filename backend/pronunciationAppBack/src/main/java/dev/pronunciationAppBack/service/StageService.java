package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Stage;
import dev.pronunciationAppBack.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public Stage createStage(Stage stage) {
        return stageRepository.save(stage);
    }

    public Optional<Stage> getStageById(Long id) {
        return stageRepository.findById(id);
    }

    public List<Stage> getAllStages() {
        return stageRepository.findAll();
    }

    public Stage updateStage(Long id, Stage stageDetails) {
        Optional<Stage> optionalStage = stageRepository.findById(id);
        if (optionalStage.isPresent()) {
            Stage stage = optionalStage.get();
            stage.setName(stageDetails.getName());
            stage.setAvatarUrl(stageDetails.getAvatarUrl());
            stage.setStatus(stageDetails.getStatus());
            stage.setProgress(stageDetails.getProgress());
            stage.setCurrentScore(stageDetails.getCurrentScore());
            stage.setGameProgresses(stageDetails.getGameProgresses());
            stage.setStageWords(stageDetails.getStageWords());
            return stageRepository.save(stage);
        } else {
            return null;
        }
    }

    public void deleteStage(Long id) {
        stageRepository.deleteById(id);
    }
}
