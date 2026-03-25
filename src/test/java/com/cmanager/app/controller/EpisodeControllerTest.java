package com.cmanager.app.controller;

import com.cmanager.app.application.controller.EpisodeController;
import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.data.EpisodeDTO;
import com.cmanager.app.application.service.EpisodeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EpisodeControllerTest {

    private EpisodeService episodeService;
    private EpisodeController episodeController;

    @BeforeEach
    void setUp() {
        episodeService = mock(EpisodeService.class);
        episodeController = new EpisodeController(episodeService);
    }

    @Test
    void testAverageRating_returnsExpectedDTO() {
        // Arrange
        EpisodeAverageDTO dto = new EpisodeAverageDTO(1, 1, 8.5);
        when(episodeService.averageRatingBySeason(1, 1)).thenReturn(dto);

        // Act
        var response = episodeController.averageRating(1, 1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(8.5, response.getBody().averageRating());
        verify(episodeService, times(1)).averageRatingBySeason(1, 1);
    }

    @Test
    void testGetEpisodesByShow_returnsList() {
        // Arrange
        EpisodeDTO ep1 = new EpisodeDTO("101", "Pilot", 1, 1);
        EpisodeDTO ep2 = new EpisodeDTO("102", "Episode 2", 1, 2);
        when(episodeService.findByShow(1)).thenReturn(List.of(ep1, ep2));

        // Act
        var response = episodeController.getEpisodesByShow(1);

        // Assert
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(2, response.getBody().size());
        assertEquals("Pilot", response.getBody().get(0).name());
        verify(episodeService, times(1)).findByShow(1);
    }
}

