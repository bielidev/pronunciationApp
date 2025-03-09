package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pronunciations")
public class PronunciationController {

    @Autowired
    private PronunciationService pronunciationService;

    @PostMapping
    public ResponseEntity<Pronunciation> createPronunciation(@RequestBody Pronunciation pronunciation) {
        Pronunciation createdPronunciation = pronunciationService.createPronunciation(pronunciation);
        return ResponseEntity.ok(createdPronunciation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pronunciation> getPronunciationById(@PathVariable Long id) {
        Optional<Pronunciation> pronunciation = pronunciationService.getPronunciationById(id);
        return pronunciation.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Pronunciation>> getAllPronunciations() {
        List<Pronunciation> pronunciations = pronunciationService.getAllPronunciations();
        return ResponseEntity.ok(pronunciations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pronunciation> updatePronunciation(@PathVariable Long id, @RequestBody Pronunciation pronunciationDetails) {
        Pronunciation updatedPronunciation = pronunciationService.updatePronunciation(id, pronunciationDetails);
        if (updatedPronunciation != null) {
            return ResponseEntity.ok(updatedPronunciation);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePronunciation(@PathVariable Long id) {
        pronunciationService.deletePronunciation(id);
        return ResponseEntity.noContent().build();
    }
}
