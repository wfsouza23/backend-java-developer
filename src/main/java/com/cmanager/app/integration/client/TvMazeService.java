package com.cmanager.app.integration.client;

import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.repository.EpisodeRepository;
import com.cmanager.app.application.repository.ShowRepository;
import com.cmanager.app.integration.dto.ShowsResponseDTO;
import com.cmanager.app.integration.dto.TvMazeEpisodeDTO;
import com.cmanager.app.integration.dto.TvMazeShowDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class TvMazeService {

    private final RestTemplate restTemplate;
    private final ShowRepository showRepository;
    private final EpisodeRepository episodeRepository;

    public TvMazeService(RestTemplate restTemplate, ShowRepository showRepository, EpisodeRepository episodeRepository) {
        this.restTemplate = restTemplate;
        this.showRepository = showRepository;
        this.episodeRepository = episodeRepository;
    }

    public ShowsResponseDTO syncShow(String showName) {
        // URL já trazendo episódios embutidos
        String urlShow = "https://api.tvmaze.com/singlesearch/shows?q={name}&embed=episodes";

        // DTO precisa refletir a estrutura com "_embedded"
        TvMazeShowDTO showDTO = restTemplate.getForObject(urlShow, TvMazeShowDTO.class, showName);

        if (showDTO == null) {
            throw new RuntimeException("Show não encontrado na API TVMaze");
        }

        // Converte TvMazeShowDTO para entidade Show
        Show show = new Show();
        show.setId(showDTO.getId().toString());
        show.setName(showDTO.getName());
        show.setLanguage(showDTO.getLanguage());
        show.setSummary(showDTO.getSummary());
        show.setStatus(showDTO.getStatus());
        show.setRuntime(showDTO.getRuntime());
        show.setAverageRuntime(showDTO.getAverageRuntime());
        show.setOfficialSite(showDTO.getOfficialSite());
        show.setRating(showDTO.getRating().average());

        if (!showRepository.existsById(show.getId())) {
            showRepository.save(show);
        }

        // Agora os episódios vêm dentro de showDTO.getEmbedded().getEpisodes()
        if (showDTO.get_embedded() != null && showDTO.get_embedded().getEpisodes() != null) {
            for (TvMazeEpisodeDTO epDTO : showDTO.get_embedded().getEpisodes()) {
                if (!episodeRepository.existsByIdIntegration(epDTO.getId().toString())) {
                    Episode ep = new Episode();
                    ep.setIdIntegration(epDTO.getId().toString());
                    ep.setName(epDTO.getName());
                    ep.setSeason(epDTO.getSeason());
                    ep.setNumber(epDTO.getNumber());
                    ep.setType(epDTO.getType());
                    ep.setAirdate(epDTO.getAirdate());
                    ep.setAirtime(epDTO.getAirtime());
                    ep.setRuntime(epDTO.getRuntime());
                    ep.setRating(epDTO.getRating().average());
                    ep.setSummary(epDTO.getSummary());

                    ep.setShow(show);

                    episodeRepository.save(ep);
                }
            }
        }

        // Agora retorna o DTO de resposta
        return new ShowsResponseDTO(
                showDTO.getId(),
                showDTO.getName(),
                showDTO.getType(),
                showDTO.getLanguage(),
                showDTO.getStatus(),
                showDTO.getRuntime(),
                showDTO.getAverageRuntime(),
                showDTO.getOfficialSite(),
                showDTO.getRating(),
                showDTO.getSummary(),
                new ShowsResponseDTO.Embedded(showDTO.get_embedded().getEpisodes())
        );
    }

}

