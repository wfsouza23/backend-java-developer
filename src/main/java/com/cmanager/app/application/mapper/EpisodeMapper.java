package com.cmanager.app.application.mapper;

import com.cmanager.app.application.data.EpisodeResponseDTO;
import com.cmanager.app.application.domain.Episode;
import org.springframework.stereotype.Component;

@Component
public class EpisodeMapper {

    public static EpisodeResponseDTO toDTO(Episode episode) {
        return new EpisodeResponseDTO(
                episode.getIdIntegration(),
                episode.getName(),
                episode.getSeason(),
                episode.getNumber()
        );
    }
}
