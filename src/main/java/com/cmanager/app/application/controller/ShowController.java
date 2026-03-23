package com.cmanager.app.application.controller;

import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.service.ShowService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public List<Show> listShows() {
        return showService.findAll();
    }

    @PostMapping
    public Show createShow(@RequestBody Show show) {
        return showService.save(show);
    }
}
