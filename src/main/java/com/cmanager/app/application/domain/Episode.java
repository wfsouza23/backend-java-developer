package com.cmanager.app.application.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Entity
@Table(name = "EPISODE")
@Getter
@Setter
public class Episode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ID_INTEGRATION", nullable = false, unique = true)
    private Integer idIntegration;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_show", nullable = false)
    private Show show;

    private String name;
    private Integer season;
    private Integer number;
    private String type;

    private LocalDate airdate;
    private String airtime;
    private OffsetDateTime airstamp;

    private Integer runtime;

    @Column(precision = 3, scale = 1)
    private BigDecimal rating;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

}

