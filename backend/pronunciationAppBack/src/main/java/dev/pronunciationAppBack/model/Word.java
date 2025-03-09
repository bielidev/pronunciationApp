package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "text", nullable = false)
    private String text;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "sentence", nullable = false)
    private String sentence;

    @Column(name = "difficulty", nullable = false)
    private int difficulty;

    @Column(name = "is_common", nullable = false)
    private boolean isCommon;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<StageWords> stageWords;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Pronunciation> pronunciations;

    @OneToMany(mappedBy = "word", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Attempt> attempts;

    @ManyToOne
    @JoinColumn(name = "level_id")
    private Level level;

    @ManyToMany
    @JoinTable(
        name = "word_category",
        joinColumns = @JoinColumn(name = "word_id"),
        inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    @ManyToOne
    @JoinColumn(name = "stage_id")
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}