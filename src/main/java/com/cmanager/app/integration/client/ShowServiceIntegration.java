package com.cmanager.app.integration.client;

import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.mapper.EpisodeMapper;
import com.cmanager.app.application.mapper.ShowMapper;
import com.cmanager.app.application.repository.EpisodeRepository;
import com.cmanager.app.application.repository.ShowRepository;
import com.cmanager.app.core.exception.AlreadyExistsException;
import com.cmanager.app.integration.dto.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowServiceIntegration {

    private final RequestService requestService;
    private final ShowRepository showRepository;
    private final EpisodeRepository episodeRepository;
    private final ShowMapper showMapper;
    private final EpisodeMapper episodeMapper;

    public ShowServiceIntegration(RequestService requestService,
                                  ShowRepository showRepository,
                                  EpisodeRepository episodeRepository,
                                  ShowMapper showMapper,
                                  EpisodeMapper episodeMapper) {
        this.requestService = requestService;
        this.showRepository = showRepository;
        this.episodeRepository = episodeRepository;
        this.showMapper = showMapper;
        this.episodeMapper = episodeMapper;
    }

    @Transactional
    public ShowsResponseDTO syncShow(String showName) {
        Optional<Show> existing = showRepository.findByNameIgnoreCase(showName);
        if (existing.isPresent()) {
            throw new AlreadyExistsException("Show", showName);
        }

        ShowsRequestDTO dto = requestService.getShow(showName);

        Show show = showRepository.findById(dto.id().toString())
                .orElseGet(() -> showRepository.save(showMapper.toEntity(dto)));

        if (episodeRepository.findByShowIdIntegration(show.getIdIntegration()).isEmpty()) {
            dto._embedded().episodes().forEach(epDTO -> {
                Episode episode = episodeMapper.toEntity(epDTO, show);
                episodeRepository.save(episode);
            });
        }

        // Carregar episódios para o mapper
        show.setEpisodes(episodeRepository.findByShowIdIntegration(show.getIdIntegration()));

        return showMapper.toResponseDTO(show);
    }
}