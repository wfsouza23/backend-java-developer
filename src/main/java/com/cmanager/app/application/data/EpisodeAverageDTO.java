package com.cmanager.app.application.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;


@Schema(name = "EpisodeAverageDTO", description = "Response da nota média de avaliação dos episodios por temporada")
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeAverageDTO(

        @JsonProperty("showId")
        @Schema(description = "Id do show")
        Integer showId,

        @JsonProperty("season")
        @Schema(description = "Número da temporada")
        Integer season,

        @JsonProperty("averageRating")
        @Schema(name = "averageRating", description = "Nota média de avaliação dos episodios")
        Double averageRating

) {
}

