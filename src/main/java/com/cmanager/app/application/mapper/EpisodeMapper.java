package com.cmanager.app.application.mapper;

import com.cmanager.app.application.data.EpisodeDTO;
import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.integration.dto.EpisodeRequestDTO;
import com.cmanager.app.integration.dto.EpisodeResponseDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EpisodeMapper {

    public Episode toEntity(EpisodeRequestDTO dto, Show show) {
        Episode episode = new Episode();
        episode.setIdIntegration(dto.id());
        episode.setShow(show);
        episode.setName(dto.name());
        episode.setSeason(dto.season());
        episode.setNumber(dto.number());
        episode.setType(dto.type());

        if (dto.airdate() != null && !dto.airdate().isBlank()) {
            episode.setAirdate(LocalDate.parse(dto.airdate()));
        }

        episode.setAirtime(dto.airtime());
        episode.setRuntime(dto.runtime());

        if (dto.rating() != null && dto.rating().average() != null) {
            episode.setRating(dto.rating().average());
        }

        episode.setSummary(dto.summary());
        return episode;
    }

    public EpisodeResponseDTO toResponseDTO(Episode episode) {
        return new EpisodeResponseDTO(
                episode.getId(),
                episode.getName(),
                episode.getSeason(),
                episode.getNumber(),
                episode.getType(),
                episode.getAirdate() != null ? episode.getAirdate().toString() : null,
                episode.getAirtime(),
                episode.getAirstamp(),
                episode.getRuntime(),
                new com.cmanager.app.integration.dto.RatingDTO(episode.getRating()),
                episode.getSummary()
        );
    }

    public List<EpisodeDTO> toEpisodeDTO(List<Episode> episodes) {
        return episodes.stream()
                .map(e -> new EpisodeDTO(
                        e.getId().toString(),
                        e.getName(),
                        e.getSeason(),
                        e.getNumber()
                ))
                .toList();
    }
}
