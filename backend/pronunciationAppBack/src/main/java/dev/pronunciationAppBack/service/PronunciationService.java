package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Pronunciation;
import dev.pronunciationAppBack.repository.PronunciationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PronunciationService {

    @Autowired
    private PronunciationRepository pronunciationRepository;

    public Pronunciation createPronunciation(Pronunciation pronunciation) {
        return pronunciationRepository.save(pronunciation);
    }

    public Optional<Pronunciation> getPronunciationById(Long id) {
        return pronunciationRepository.findById(id);
    }

    public List<Pronunciation> getAllPronunciations() {
        return pronunciationRepository.findAll();
    }

    public Pronunciation updatePronunciation(Long id, Pronunciation pronunciationDetails) {
        Optional<Pronunciation> optionalPronunciation = pronunciationRepository.findById(id);
        if (optionalPronunciation.isPresent()) {
            Pronunciation pronunciation = optionalPronunciation.get();
            pronunciation.setAudioName(pronunciationDetails.getAudioName());
            pronunciation.setAudioSize(pronunciationDetails.getAudioSize());
            pronunciation.setAudioUrl(pronunciationDetails.getAudioUrl());
            pronunciation.setPhoneticSpelling(pronunciationDetails.getPhoneticSpelling());
            pronunciation.setSpeakerGender(pronunciationDetails.getSpeakerGender());
            pronunciation.setType(pronunciationDetails.getType());
            pronunciation.setAccuracyScore(pronunciationDetails.getAccuracyScore());
            pronunciation.setWord(pronunciationDetails.getWord());
            return pronunciationRepository.save(pronunciation);
        } else {
            return null;
        }
    }

    public void deletePronunciation(Long id) {
        pronunciationRepository.deleteById(id);
    }
}
