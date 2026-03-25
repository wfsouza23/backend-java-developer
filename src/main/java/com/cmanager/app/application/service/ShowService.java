package com.cmanager.app.application.service;

import com.cmanager.app.application.data.ShowRequestDTO;
import com.cmanager.app.application.data.ShowResponseDTO;
import com.cmanager.app.application.domain.Episode;
import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.mapper.EpisodeMapper;
import com.cmanager.app.application.mapper.ShowMapper;
import com.cmanager.app.application.repository.ShowRepository;
import com.cmanager.app.core.data.PageResultResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public ShowResponseDTO save(ShowRequestDTO showRequest) {
        // Converter DTO -> Entity manualmente
        Show show = new Show();
        show.setIdIntegration(showRequest.idIntegration());
        show.setName(showRequest.name());
        show.setType(showRequest.type());
        show.setLanguage(showRequest.language());
        show.setStatus(showRequest.status());
        show.setRuntime(showRequest.runtime());
        show.setAverageRuntime(showRequest.averageRuntime());
        show.setOfficialSite(showRequest.officialSite());
        show.setRating(showRequest.rating());
        show.setSummary(showRequest.summary());

        // Salvar no banco
        Show saved = showRepository.save(show);

        // Converter Entity -> ResponseDTO manualmente
        return new ShowResponseDTO(
                saved.getIdIntegration(),
                saved.getName(),
                saved.getLanguage(),
                saved.getStatus(),
                saved.getRuntime(),
                saved.getAverageRuntime(),
                saved.getOfficialSite(),
                saved.getSummary()
        );
    }

    public List<Show> findAll() {
        return showRepository.findAll();
    }

    public Show findById(String id) {
        return showRepository.findById(id).orElse(null);
    }

    public ShowResponseDTO findByShow(Integer idIntegration) {
        Show show = showRepository.findByIdIntegration(idIntegration)
                .orElseThrow(() -> new IllegalArgumentException("Episódio não encontrado"));

        return ShowMapper.toDTO(show);
    }

    public PageResultResponse<ShowResponseDTO> getShows(String nameFilter, Pageable pageable) {
        Page<Show> shows;
        if (nameFilter != null && !nameFilter.isBlank()) {
            shows = showRepository.findByNameContainingIgnoreCase(nameFilter, pageable);
        } else {
            shows = showRepository.findAll(pageable);
        }

        List<ShowResponseDTO> content = shows.getContent()
                .stream()
                .map(ShowMapper::toDTO)
                .toList();

        return new PageResultResponse<>(
                content,
                shows.getTotalElements(),
                shows.getNumber(),
                shows.getSize()
        );
    }
}
