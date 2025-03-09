package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.StageWords;
import dev.pronunciationAppBack.repository.StageWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageWordsService {

    @Autowired
    private StageWordsRepository stageWordsRepository;

    public StageWords createStageWords(StageWords stageWords) {
        return stageWordsRepository.save(stageWords);
    }

    public Optional<StageWords> getStageWordsById(Long id) {
        return stageWordsRepository.findById(id);
    }

    public List<StageWords> getAllStageWords() {
        return stageWordsRepository.findAll();
    }

    public StageWords updateStageWords(Long id, StageWords stageWordsDetails) {
        Optional<StageWords> optionalStageWords = stageWordsRepository.findById(id);
        if (optionalStageWords.isPresent()) {
            StageWords stageWords = optionalStageWords.get();
            stageWords.setStatus(stageWordsDetails.getStatus());
            stageWords.setLastUpdateDateTime(stageWordsDetails.getLastUpdateDateTime());
            stageWords.setStage(stageWordsDetails.getStage());
            stageWords.setWord(stageWordsDetails.getWord());
            return stageWordsRepository.save(stageWords);
        } else {
            return null;
        }
    }

    public void deleteStageWords(Long id) {
        stageWordsRepository.deleteById(id);
    }
}
