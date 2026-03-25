package com.cmanager.app.integration.client;

import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.repository.EpisodeRepository;
import com.cmanager.app.application.repository.ShowRepository;
import com.cmanager.app.integration.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShowServiceIntegration {

    private final RequestService requestService;
    private final ShowRepository showRepository;
    private final EpisodeRepository episodeRepository;

    public ShowServiceIntegration(RequestService requestService,
                                  ShowRepository showRepository,
                                  EpisodeRepository episodeRepository) {
        this.requestService = requestService;
        this.showRepository = showRepository;
        this.episodeRepository = episodeRepository;
    }

    @Transactional
    public ShowsResponseDTO syncShow(String showName) {
        ShowsRequestDTO dto = requestService.getShow(showName);

        Show show = showRepository.findById(dto.id().toString())
                .orElseGet(() -> persistShow(dto));

        if (show.getEpisodes() == null || show.getEpisodes().isEmpty()) {
            persistEpisodes(dto._embedded().episodes(), show);
        }

        return toResponseDTO(show);
    }

    private Show persistShow(ShowsRequestDTO dto) {
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
        return showRepository.save(show);
    }

    private void persistEpisodes(List<EpisodeRequestDTO> episodesDTO, Show show) {
        for (EpisodeRequestDTO ep : episodesDTO) {
            Episode episode = new Episode();
            episode.setIdIntegration(ep.id());
            episode.setShow(show);
            episode.setName(ep.name());
            episode.setSeason(ep.season());
            episode.setNumber(ep.number());
            episode.setType(ep.type());

            if (isValidDate(ep.airdate())) {
                episode.setAirdate(LocalDate.parse(ep.airdate()));
            }
            episode.setAirtime(ep.airtime());
            episode.setRuntime(ep.runtime());

            if (ep.rating() != null && ep.rating().average() != null) {
                episode.setRating(ep.rating().average());
            }

            episode.setSummary(ep.summary());
            episodeRepository.save(episode);
        }
    }

    private boolean isValidDate(String date) {
        return date != null && !date.isBlank();
    }

    private ShowsResponseDTO toResponseDTO(Show show) {
        List<EpisodeResponseDTO> episodes = episodeRepository.findByShowIdIntegration(show.getIdIntegration())
                .stream()
                .map(ep -> new EpisodeResponseDTO(
                        ep.getId(),
                        ep.getName(),
                        ep.getSeason(),
                        ep.getNumber(),
                        ep.getType(),
                        ep.getAirdate() != null ? ep.getAirdate().toString() : null,
                        ep.getAirtime(),
                        ep.getAirstamp(),
                        ep.getRuntime(),
                        new RatingDTO(ep.getRating()),
                        ep.getSummary()
                ))
                .toList();

        return new ShowsResponseDTO(
                show.getId(),
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
