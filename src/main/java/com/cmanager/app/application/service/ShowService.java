package com.cmanager.app.application.service;

import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.mapper.ShowMapper;
import com.cmanager.app.application.repository.ShowRepository;
import com.cmanager.app.core.data.PageResultResponse;
import com.cmanager.app.integration.dto.ShowsRequestDTO;
import com.cmanager.app.integration.dto.ShowsResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowMapper showMapper;

    public ShowsResponseDTO save(ShowsRequestDTO dto) {
        Show show = showMapper.toEntity(dto);
        Show saved = showRepository.save(show);
        return showMapper.toResponseDTO(saved);
    }

    public ShowsResponseDTO findByShow(Integer idIntegration) {
        Show show = showRepository.findByIdIntegration(idIntegration)
                .orElseThrow(() -> new IllegalArgumentException("Show não encontrado"));
        return showMapper.toResponseDTO(show);
    }

    public PageResultResponse<ShowsResponseDTO> getShows(String nameFilter, Pageable pageable) {
        Page<Show> shows = (nameFilter != null && !nameFilter.isBlank())
                ? showRepository.findByNameContainingIgnoreCase(nameFilter, pageable)
                : showRepository.findAll(pageable);

        List<ShowsResponseDTO> content = shows.getContent()
                .stream()
                .map(showMapper::toResponseDTO)
                .toList();

        return new PageResultResponse<>(
                content,
                shows.getTotalElements(),
                shows.getNumber(),
                shows.getSize()
        );
    }
}
