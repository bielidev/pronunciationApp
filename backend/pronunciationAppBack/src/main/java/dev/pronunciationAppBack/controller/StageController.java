package dev.pronunciationAppBack.controller;

import dev.pronunciationAppBack.model.*;
import dev.pronunciationAppBack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @PostMapping
    public ResponseEntity<Stage> createStage(@RequestBody Stage stage) {
        Stage createdStage = stageService.createStage(stage);
        return ResponseEntity.ok(createdStage);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Stage> getStageById(@PathVariable Long id) {
        Optional<Stage> stage = stageService.getStageById(id);
        return stage.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Stage>> getAllStages() {
        List<Stage> stages = stageService.getAllStages();
        return ResponseEntity.ok(stages);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stage> updateStage(@PathVariable Long id, @RequestBody Stage stageDetails) {
        Stage updatedStage = stageService.updateStage(id, stageDetails);
        if (updatedStage != null) {
            return ResponseEntity.ok(updatedStage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStage(@PathVariable Long id) {
        stageService.deleteStage(id);
        return ResponseEntity.noContent().build();
    }
}
