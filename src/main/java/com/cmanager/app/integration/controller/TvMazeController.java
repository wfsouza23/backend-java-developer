package com.cmanager.app.integration.controller;

import com.cmanager.app.application.domain.Show;
import com.cmanager.app.integration.client.TvMazeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tvmaze")
public class TvMazeController {

    private final TvMazeService tvMazeService;

    public TvMazeController(TvMazeService tvMazeService) {
        this.tvMazeService = tvMazeService;
    }

    @PostMapping("/sync/{showName}")
    public Show syncShow(@PathVariable String showName) {
        return tvMazeService.syncShow(showName);
    }
}
