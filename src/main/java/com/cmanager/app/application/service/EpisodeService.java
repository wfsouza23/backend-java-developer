package com.cmanager.app.application.service;

import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.repository.EpisodeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    public EpisodeService(EpisodeRepository episodeRepository) {
        this.episodeRepository = episodeRepository;
    }

    public Episode save(Episode episode) {
        return episodeRepository.save(episode);
    }

    public List<Episode> findByShow(String showId) {
        return episodeRepository.findByShowId(showId);
    }

//    public double averageRatingBySeason(String showId, Integer season) {
//        List<Episode> episodes = episodeRepository.findByShowIdAndSeason(showId, season);
//        OptionalDouble avg = episodes.stream()
//                .filter(e -> e.getRating() != null)
//                .mapToDouble(e -> e.getRating().doubleValue())
//                .average();
//        return avg.orElse(0.0);
//    }
}

