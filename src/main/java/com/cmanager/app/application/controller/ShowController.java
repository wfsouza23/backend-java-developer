package com.cmanager.app.application.controller;

import com.cmanager.app.application.data.*;
import com.cmanager.app.application.service.ShowService;
import com.cmanager.app.core.data.PageResultResponse;
import com.cmanager.app.integration.client.RequestService;
import com.cmanager.app.integration.dto.ShowsRequestDTO;
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

    private final RequestService requestService;

    public ShowController(ShowService showService, RequestService requestService) {
        this.showService = showService;
        this.requestService = requestService;
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

    @PostMapping("/{showName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShowsRequestDTO> createShow(@PathVariable String showName) {

        return ResponseEntity.ok().body(requestService.getShow(showName));
    }
}
