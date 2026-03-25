package com.cmanager.app.application.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "show")
@Getter
@Setter
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "ID_INTEGRATION", nullable = false, unique = true)
    private Integer idIntegration;

    private String name;
    private String type;
    private String language;
    private String status;
    private Integer runtime;
    private Integer averageRuntime;
    private String officialSite;
    private BigDecimal rating;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Episode> episodes;

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

