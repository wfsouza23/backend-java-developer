package com.cmanager.app.application.controller;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.data.EpisodeDTO;
import com.cmanager.app.application.service.EpisodeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@RestController
@RequestMapping("/api/episodes")
@RequiredArgsConstructor
@Tag(
        name = "EpisodeController",
        description = "APIs de busca de episodios"
)
public class EpisodeController {

    private final EpisodeService episodeService;

    @Operation(
            summary = "get",
            description = "Consulta um show pelo id e por temporada",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
            }
    )
    @GetMapping("/{showId}/season/{season}/episodes/average-rating")
    public ResponseEntity<EpisodeAverageDTO> averageRating(@PathVariable Integer showId, @PathVariable Integer season) {
        EpisodeAverageDTO response = episodeService.averageRatingBySeason(showId, season);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "get",
            description = "Busca por lista de Episodios por id do Show",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta realizada com sucesso")
            }
    )
    @GetMapping("/show/{showId}")
    public ResponseEntity<List<EpisodeDTO>> getEpisodesByShow(@PathVariable Integer showId) {
        return ResponseEntity.ok(episodeService.findByShow(showId));
    }

}