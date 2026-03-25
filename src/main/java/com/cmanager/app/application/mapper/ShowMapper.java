package com.cmanager.app.application.mapper;

import com.cmanager.app.application.domain.Show;
import com.cmanager.app.integration.dto.RatingDTO;
import com.cmanager.app.integration.dto.ShowsRequestDTO;
import com.cmanager.app.integration.dto.ShowsResponseDTO;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShowMapper {

    private final EpisodeMapper episodeMapper;

    public ShowMapper(EpisodeMapper episodeMapper) {
        this.episodeMapper = episodeMapper;
    }

    public Show toEntity(ShowsRequestDTO dto) {
        Show show = new Show();
        show.setIdIntegration(dto.id());
        show.setName(dto.name());
        show.setLanguage(dto.language());
        show.setType(dto.type());
        show.setStatus(dto.status());
        show.setRuntime(dto.runtime());
        show.setAverageRuntime(dto.averageRuntime());
        show.setOfficialSite(dto.officialSite());
        show.setSummary(dto.summary());
        return show;
    }

    public ShowsResponseDTO toResponseDTO(Show show) {
        var episodes = show.getEpisodes() == null ?
                null :
                show.getEpisodes().stream()
                        .map(episodeMapper::toResponseDTO)
                        .collect(Collectors.toList());

        return new ShowsResponseDTO(
                show.getIdIntegration().toString(),
                show.getName(),
                show.getType(),
                show.getLanguage(),
                show.getStatus(),
                show.getRuntime(),
                show.getAverageRuntime(),
                show.getOfficialSite(),
                new RatingDTO(show.getRating()),
                show.getSummary(),
                new ShowsResponseDTO.Embedded(episodes)
        );
    }
}
