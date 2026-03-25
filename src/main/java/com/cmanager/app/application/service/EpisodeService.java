package com.cmanager.app.application.service;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.data.EpisodeDTO;
import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.mapper.EpisodeMapper;
import com.cmanager.app.application.repository.EpisodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    private final EpisodeMapper episodeMapper;

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

    public List<EpisodeDTO> findByShow(Integer showIdIntegration) {
        return episodeMapper.toEpisodeDTO(episodeRepository.findByShow_IdIntegration(showIdIntegration));
    }
}
