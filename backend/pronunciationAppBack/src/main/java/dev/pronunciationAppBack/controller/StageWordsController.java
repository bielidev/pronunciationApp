package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stagewords")
public class StageWordsController {

    @Autowired
    private StageWordsService stageWordsService;

    @PostMapping
    public ResponseEntity<StageWords> createStageWords(@RequestBody StageWords stageWords) {
        StageWords createdStageWords = stageWordsService.createStageWords(stageWords);
        return ResponseEntity.ok(createdStageWords);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StageWords> getStageWordsById(@PathVariable Long id) {
        Optional<StageWords> stageWords = stageWordsService.getStageWordsById(id);
        return stageWords.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<StageWords>> getAllStageWords() {
        List<StageWords> stageWords = stageWordsService.getAllStageWords();
        return ResponseEntity.ok(stageWords);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StageWords> updateStageWords(@PathVariable Long id, @RequestBody StageWords stageWordsDetails) {
        StageWords updatedStageWords = stageWordsService.updateStageWords(id, stageWordsDetails);
        if (updatedStageWords != null) {
            return ResponseEntity.ok(updatedStageWords);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStageWords(@PathVariable Long id) {
        stageWordsService.deleteStageWords(id);
        return ResponseEntity.noContent().build();
    }
}
