package dev.pronunciationAppBack.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "user_id", insertable = false, updatable = false)
    private String userId;

    @Column(name = "word_id", insertable = false, updatable = false)
    private String wordId;

    @Column(name = "timestamp", nullable = false)
    private Date timestamp;

    @Column(name = "score", nullable = false)
    private float score;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word;
}