package com.cmanager.app.application.service;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeAverageDTO averageRatingBySeason(Integer showId, Integer season) {
        double average = episodeRepository.findByShowIdIntegrationAndSeason(showId, season)
                .stream()
                .filter(e -> e.getRating() != null)
                .mapToDouble(e -> e.getRating().doubleValue())
                .average()
                .orElse(0.0);

        double rounded = Math.round(average * 100.0) / 100.0;
        return new EpisodeAverageDTO(showId, season, rounded);
    }
}
