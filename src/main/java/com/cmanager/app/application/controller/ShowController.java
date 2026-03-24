package com.cmanager.app.application.controller;

import com.cmanager.app.application.data.*;
import com.cmanager.app.application.service.ShowService;
import com.cmanager.app.core.data.PageResultResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping
    public ResponseEntity<PageResultResponse<ShowResponseDTO>> getShows(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(showService.getShows(name, pageable));
    }

    @GetMapping("/integration/{idIntegration}")
    public ResponseEntity<ShowResponseDTO> findEpisodes(@PathVariable String idIntegration) {
        return ResponseEntity.ok(showService.findByShow(idIntegration));
    }

    @PostMapping
    public ResponseEntity<ShowResponseDTO> createShow(@RequestBody ShowRequestDTO showRequest) {
        URI location = URI.create("/api/shows/" + showRequest.idIntegration());

        return ResponseEntity.created(location).body(showService.save(showRequest));
    }
}
