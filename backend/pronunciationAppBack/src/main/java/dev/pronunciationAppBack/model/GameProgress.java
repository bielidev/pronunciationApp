package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class GameProgress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "current_score", nullable = false)
    private int currentScore;

    @Column(name = "current_stage", nullable = false)
    private int currentStage;

    @Column(name = "last_played_date", nullable = false)
    private Date lastPlayedDate;

    @Column(name = "words_learned", nullable = false)
    private int wordsLearned;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    public enum Status {
        IN_PROGRESS, COMPLETED, NOT_STARTED
    }
}