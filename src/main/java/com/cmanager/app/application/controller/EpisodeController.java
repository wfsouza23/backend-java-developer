package com.cmanager.app.application.controller;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.data.EpisodeRequestDTO;
import com.cmanager.app.application.data.EpisodeResponseDTO;
import com.cmanager.app.application.service.EpisodeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/episodes")
public class EpisodeController {

    private final EpisodeService episodeService;

    public EpisodeController(EpisodeService episodeService) {
        this.episodeService = episodeService;
    }

//    @GetMapping("/{showId}/episodes")
//    public ResponseEntity<List<EpisodeResponseDTO>> listEpisodes(@PathVariable String showId) {
//        return ResponseEntity.ok(episodeService.findByShow(showId));
//    }
//
//    @GetMapping("/integration/{idIntegration}")
//    public ResponseEntity<EpisodeResponseDTO> findEpisodes(@PathVariable String idIntegration) {
//        return ResponseEntity.ok(episodeService.findByEpisode(idIntegration));
//    }
//
//    @GetMapping("/{showId}/season/{season}/episodes/average-rating")
//    public ResponseEntity<EpisodeAverageDTO> averageRating(@PathVariable String showId, @PathVariable Integer season) {
//        EpisodeAverageDTO response = episodeService.averageRatingBySeason(showId, season);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping
//    public ResponseEntity<EpisodeResponseDTO> createEpisode(@RequestBody EpisodeRequestDTO episodeRequest) {
//        URI location = URI.create("/api/episodes/integration/" + episodeRequest.idIntegration());
//
//        return ResponseEntity.created(location).body(episodeService.save(episodeRequest));
//    }
}