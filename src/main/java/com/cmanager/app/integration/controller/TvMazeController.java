package com.cmanager.app.integration.controller;

import com.cmanager.app.integration.client.TvMazeService;
import com.cmanager.app.integration.dto.ShowsResponseDTO;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/tvmaze")
public class TvMazeController {

    private final TvMazeService tvMazeService;

    public TvMazeController(TvMazeService tvMazeService) {
        this.tvMazeService = tvMazeService;
    }

    @PostMapping("/sync/{showName}")
    public ResponseEntity<ShowsResponseDTO> syncShow(@PathVariable String showName) {
        return ResponseEntity.ok(tvMazeService.syncShow(showName));
    }
}
