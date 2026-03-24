package com.cmanager.app.application.mapper;

import com.cmanager.app.application.data.ShowResponseDTO;
import com.cmanager.app.application.domain.Show;

public class ShowMapper {

    public static ShowResponseDTO toDTO(Show show) {
        return new ShowResponseDTO(
                show.getIdIntegration(),
                show.getName(),
                show.getLanguage(),
                show.getStatus(),
                show.getRuntime(),
                show.getAverageRuntime(),
                show.getOfficialSite(),
                show.getSummary()
        );
    }
}

