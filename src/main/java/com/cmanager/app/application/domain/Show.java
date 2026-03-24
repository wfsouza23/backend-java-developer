package com.cmanager.app.application.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "SHOW")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Show {

    @Id
    private String id;

    @Column(name = "ID_INTEGRATION", nullable = false, unique = true)
    private String idIntegration;

    @Column(name = "NAME")
    private String name;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "LANGUAGE")
    private String language;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "RUNTIME")
    private Integer runtime;

    @Column(name = "AVERAGE_RUNTIME")
    private Integer averageRuntime;

    @Column(name = "OFFICIAL_SITE")
    private String officialSite;

    @Column(name = "RATING", precision = 5, scale = 2)
    private BigDecimal rating;

    @Column(name = "SUMMARY", columnDefinition = "TEXT")
    private String summary;

    @Column(name = "created_at", nullable = false, updatable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMPTZ")
    private OffsetDateTime updatedAt;

    @OneToMany(mappedBy = "show", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Episode> episodes = new ArrayList<>();


    @PrePersist
    public void prePersist() {
        final OffsetDateTime now = OffsetDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;

        // gera UUID para id primário
        if (this.id == null) {
            this.id = UUID.randomUUID().toString();
        }

        // gera UUID para idIntegration
        if (this.idIntegration == null) {
            this.idIntegration = UUID.randomUUID().toString();
        }
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = OffsetDateTime.now();
    }
}
