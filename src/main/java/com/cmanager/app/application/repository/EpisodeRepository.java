package com.cmanager.app.application.repository;

import com.cmanager.app.application.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findByShowIdIntegration(Integer idIntegration);
    List<Episode> findByShowIdIntegrationAndSeason(Integer idIntegration, Integer season);
    Optional<Episode> findByIdIntegration(Integer idIntegration);
    boolean existsByIdIntegration(Integer idIntegration);
}
