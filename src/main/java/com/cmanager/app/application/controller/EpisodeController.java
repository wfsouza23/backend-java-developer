package com.cmanager.app.application.controller;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/{showId}/season/{season}/episodes/average-rating")
    public ResponseEntity<EpisodeAverageDTO> averageRating(@PathVariable Integer showId, @PathVariable Integer season) {
        EpisodeAverageDTO response = episodeService.averageRatingBySeason(showId, season);
        return ResponseEntity.ok(response);
    }

}