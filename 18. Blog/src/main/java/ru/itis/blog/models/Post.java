package ru.itis.blog.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;
    private LocalDateTime createdAt;
    @Column(length = 20)
    private String title;
    @Column(columnDefinition = "text")
    private String text;
    @Enumerated(value = EnumType.STRING)
    private State state;

    public enum State {
        DRAFT, DELETED, PUBLISHED
    }


}
