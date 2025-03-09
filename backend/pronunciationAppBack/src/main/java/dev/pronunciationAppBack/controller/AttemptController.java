package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    @Autowired
    private AttemptService attemptService;

    @PostMapping
    public ResponseEntity<Attempt> createAttempt(@RequestBody Attempt attempt) {
        Attempt createdAttempt = attemptService.createAttempt(attempt);
        return ResponseEntity.ok(createdAttempt);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Attempt> getAttemptById(@PathVariable Long id) {
        Optional<Attempt> attempt = attemptService.getAttemptById(id);
        return attempt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Attempt>> getAllAttempts() {
        List<Attempt> attempts = attemptService.getAllAttempts();
        return ResponseEntity.ok(attempts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Attempt> updateAttempt(@PathVariable Long id, @RequestBody Attempt attemptDetails) {
        Attempt updatedAttempt = attemptService.updateAttempt(id, attemptDetails);
        if (updatedAttempt != null) {
            return ResponseEntity.ok(updatedAttempt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttempt(@PathVariable Long id) {
        attemptService.deleteAttempt(id);
        return ResponseEntity.noContent().build();
    }
}
