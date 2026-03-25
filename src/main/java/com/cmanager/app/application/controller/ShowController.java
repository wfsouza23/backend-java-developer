package com.cmanager.app.application.controller;

import com.cmanager.app.application.service.ShowService;
import com.cmanager.app.core.data.PageResultResponse;
import com.cmanager.app.integration.client.ShowServiceIntegration;
import com.cmanager.app.integration.dto.ShowsResponseDTO;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    private final ShowServiceIntegration showServiceIntegration;

    public ShowController(ShowService showService, ShowServiceIntegration showServiceIntegration) {
        this.showService = showService;
        this.showServiceIntegration = showServiceIntegration;
    }

    @GetMapping
    public ResponseEntity<PageResultResponse<ShowsResponseDTO>> getShows(
            @RequestParam(required = false) String name,
            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        return ResponseEntity.ok(showService.getShows(name, pageable));
    }

    @PostMapping("/{showName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShowsResponseDTO> createShow(@PathVariable String showName) {

        return ResponseEntity.ok().body(showServiceIntegration.syncShow(showName));
    }
}
