package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/levels")
public class LevelController {

    @Autowired
    private LevelService levelService;

    @PostMapping
    public ResponseEntity<Level> createLevel(@RequestBody Level level) {
        Level createdLevel = levelService.createLevel(level);
        return ResponseEntity.ok(createdLevel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Level> getLevelById(@PathVariable Long id) {
        Optional<Level> level = levelService.getLevelById(id);
        return level.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Level>> getAllLevels() {
        List<Level> levels = levelService.getAllLevels();
        return ResponseEntity.ok(levels);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Level> updateLevel(@PathVariable Long id, @RequestBody Level levelDetails) {
        Level updatedLevel = levelService.updateLevel(id, levelDetails);
        if (updatedLevel != null) {
            return ResponseEntity.ok(updatedLevel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLevel(@PathVariable Long id) {
        levelService.deleteLevel(id);
        return ResponseEntity.noContent().build();
    }
}
