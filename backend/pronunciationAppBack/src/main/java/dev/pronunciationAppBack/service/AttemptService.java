package dev.pronunciationAppBack.service;

import dev.pronunciationAppBack.model.Attempt;
import dev.pronunciationAppBack.repository.AttemptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    public Attempt createAttempt(Attempt attempt) {
        return attemptRepository.save(attempt);
    }

    public Optional<Attempt> getAttemptById(Long id) {
        return attemptRepository.findById(id);
    }

    public List<Attempt> getAllAttempts() {
        return attemptRepository.findAll();
    }

    public Attempt updateAttempt(Long id, Attempt attemptDetails) {
        Optional<Attempt> optionalAttempt = attemptRepository.findById(id);
        if (optionalAttempt.isPresent()) {
            Attempt attempt = optionalAttempt.get();
            attempt.setUser(attemptDetails.getUser());
            attempt.setWord(attemptDetails.getWord());
            attempt.setTimestamp(attemptDetails.getTimestamp());
            attempt.setScore(attemptDetails.getScore());
            return attemptRepository.save(attempt);
        } else {
            return null;
        }
    }

    public void deleteAttempt(Long id) {
        attemptRepository.deleteById(id);
    }
}
