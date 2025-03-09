package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pronunciation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "audio_name", nullable = false)
    private String audioName;

    @Column(name = "audio_size", nullable = false)
    private int audioSize;

    @Column(name = "audio_url", nullable = false)
    private String audioUrl;

    @Column(name = "phonetic_spelling", nullable = false)
    private String phoneticSpelling;

    @Column(name = "speaker_gender", nullable = false)
    private String speakerGender;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private Type type;

    @Column(name = "accuracy_score", nullable = false)
    private float accuracyScore;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;

    public enum Type {
        STANDARD, SLOW, PHONETIC
    }
}