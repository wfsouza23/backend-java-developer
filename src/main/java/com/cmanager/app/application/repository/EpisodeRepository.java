package com.cmanager.app.application.repository;

import com.cmanager.app.application.domain.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Long> {
    List<Episode> findByShowIdIntegration(Integer idIntegration);
    List<Episode> findByShowIdIntegrationAndSeason(Integer idIntegration, Integer season);
    List<Episode> findByShow_IdIntegration(Integer showIdIntegration);

}
