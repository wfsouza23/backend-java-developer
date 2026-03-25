package com.cmanager.app.application.service;

import com.cmanager.app.application.data.EpisodeAverageDTO;
import com.cmanager.app.application.data.EpisodeRequestDTO;
import com.cmanager.app.application.data.EpisodeResponseDTO;
import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.mapper.EpisodeMapper;
import com.cmanager.app.application.repository.EpisodeRepository;
import com.cmanager.app.application.repository.ShowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
public class EpisodeService {

    private final EpisodeRepository episodeRepository;

    private final ShowRepository showRepository;

    private final EpisodeMapper episodeMapper;

    public EpisodeResponseDTO save(EpisodeRequestDTO episodeRequestDTO) {
        // Converter DTO -> Entity manualmente
        Episode episode = new Episode();
        episode.setIdIntegration(episodeRequestDTO.idIntegration());
        episode.setName(episodeRequestDTO.name());
        episode.setSeason(episodeRequestDTO.season());
        episode.setNumber(episodeRequestDTO.number());
        episode.setType(episodeRequestDTO.type());
        episode.setAirdate(episodeRequestDTO.airdate());
        episode.setAirtime(episodeRequestDTO.airtime());
        //episode.setAirstamp(OffsetDateTime.parse(episodeRequestDTO.airstamp()));
        episode.setRuntime(episodeRequestDTO.runtime());
        episode.setRating(episodeRequestDTO.rating());
        episode.setSummary(episodeRequestDTO.summary());

        // Buscar o Show associado
        Show show = showRepository.findById(episodeRequestDTO.showId())
                .orElseThrow(() -> new IllegalArgumentException("Show não encontrado"));
        episode.setShow(show);

        // Salvar no banco
        Episode saved = episodeRepository.save(episode);

        // Converter Entity -> ResponseDTO manualmente
        return new EpisodeResponseDTO(
                saved.getIdIntegration(),
                saved.getName(),
                saved.getSeason(),
                saved.getNumber()
        );
    }

    public List<EpisodeResponseDTO> findByShow(Integer showId) {
        List<Episode> episodes = episodeRepository.findByShowIdIntegration(showId);

        return episodes.stream()
                .map(EpisodeMapper::toDTO)
                .toList();
    }

    public EpisodeResponseDTO findByEpisode(Integer idIntegration) {
        Episode episode = episodeRepository.findByIdIntegration(idIntegration)
                .orElseThrow(() -> new IllegalArgumentException("Episódio não encontrado"));
        return EpisodeMapper.toDTO(episode);
    }

    public EpisodeAverageDTO averageRatingBySeason(Integer showId, Integer season) {
        List<Episode> episodes = episodeRepository.findByShowIdIntegrationAndSeason(showId, season);

        double avg = episodes.stream()
                .filter(e -> e.getRating() != null)
                .mapToDouble(e -> e.getRating().doubleValue())
                .average()
                .orElse(0.0);

        double rounded = Math.round(avg * 100.0) / 100.0;

        return new EpisodeAverageDTO(showId, season, rounded);
    }
}

