package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gameprogresses")
public class GameProgressController {

    @Autowired
    private GameProgressService gameProgressService;

    @PostMapping
    public ResponseEntity<GameProgress> createGameProgress(@RequestBody GameProgress gameProgress) {
        GameProgress createdGameProgress = gameProgressService.createGameProgress(gameProgress);
        return ResponseEntity.ok(createdGameProgress);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameProgress> getGameProgressById(@PathVariable Long id) {
        Optional<GameProgress> gameProgress = gameProgressService.getGameProgressById(id);
        return gameProgress.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<GameProgress>> getAllGameProgresses() {
        List<GameProgress> gameProgresses = gameProgressService.getAllGameProgresses();
        return ResponseEntity.ok(gameProgresses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameProgress> updateGameProgress(@PathVariable Long id, @RequestBody GameProgress gameProgressDetails) {
        GameProgress updatedGameProgress = gameProgressService.updateGameProgress(id, gameProgressDetails);
        if (updatedGameProgress != null) {
            return ResponseEntity.ok(updatedGameProgress);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGameProgress(@PathVariable Long id) {
        gameProgressService.deleteGameProgress(id);
        return ResponseEntity.noContent().build();
    }
}
