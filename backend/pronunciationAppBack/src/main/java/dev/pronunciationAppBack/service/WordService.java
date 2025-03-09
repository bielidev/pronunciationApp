package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Word;
import dev.pronunciationAppBack.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WordService {

    @Autowired
    private WordRepository wordRepository;

    public Word createWord(Word word) {
        return wordRepository.save(word);
    }

    public Optional<Word> getWordById(Long id) {
        return wordRepository.findById(id);
    }

    public List<Word> getAllWords() {
        return wordRepository.findAll();
    }

    public Word updateWord(Long id, Word wordDetails) {
        Optional<Word> optionalWord = wordRepository.findById(id);
        if (optionalWord.isPresent()) {
            Word word = optionalWord.get();
            word.setText(wordDetails.getText());
            word.setDescription(wordDetails.getDescription());
            word.setSentence(wordDetails.getSentence());
            word.setDifficulty(wordDetails.getDifficulty());
            word.setCommon(wordDetails.isCommon());
            word.setStageWords(wordDetails.getStageWords());
            word.setPronunciations(wordDetails.getPronunciations());
            word.setAttempts(wordDetails.getAttempts());
            word.setLevel(wordDetails.getLevel());
            word.setCategories(wordDetails.getCategories());
            return wordRepository.save(word);
        } else {
            return null;
        }
    }

    public void deleteWord(Long id) {
        wordRepository.deleteById(id);
    }
}
