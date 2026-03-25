package com.cmanager.app.application.controller;

import com.cmanager.app.integration.client.ShowServiceIntegration;
import com.cmanager.app.integration.dto.ShowsResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/shows")
public class ShowController {

    //private final ShowService showService;

    private final ShowServiceIntegration showServiceIntegration;

    public ShowController(ShowServiceIntegration showServiceIntegration) {
        //this.showService = showService;
        //this.requestService = requestService;
        this.showServiceIntegration = showServiceIntegration;
    }

//    @GetMapping
//    public ResponseEntity<PageResultResponse<ShowResponseDTO>> getShows(
//            @RequestParam(required = false) String name,
//            @PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
//        return ResponseEntity.ok(showService.getShows(name, pageable));
//    }
//
//    @GetMapping("/integration/{idIntegration}")
//    public ResponseEntity<ShowResponseDTO> findEpisodes(@PathVariable String idIntegration) {
//        return ResponseEntity.ok(showService.findByShow(idIntegration));
//    }

    @PostMapping("/{showName}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ShowsResponseDTO> createShow(@PathVariable String showName) {

        return ResponseEntity.ok().body(showServiceIntegration.syncShow(showName));
    }
}
