package com.cmanager.app.application.controller;

import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.service.EpisodeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

    @GetMapping("/{showId}")
    public List<Episode> listEpisodes(@PathVariable String showId) {
        return episodeService.findByShow(showId);
    }

//    @GetMapping("/{showId}/season/{season}/average")
//    public double averageRating(@PathVariable String showId, @PathVariable Integer season) {
//        return episodeService.averageRatingBySeason(showId, season);
//    }

    @PostMapping
    public Episode createEpisode(@RequestBody Episode episode) {
        return episodeService.save(episode);
    }
}