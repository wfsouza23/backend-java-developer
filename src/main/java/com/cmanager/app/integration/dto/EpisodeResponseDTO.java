package com.cmanager.app.integration.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Schema(name = "EpisodeResponseDTO", description = "Objeto de resposta de Episódios")
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeResponseDTO(
        @JsonProperty("id")
        @Schema(name = "id", description = "Id do episódio")
        Long id,
        @JsonProperty("name")
        @Schema(name = "name", description = "Nome do episódio")
        String name,
        @JsonProperty("season")
        @Schema(name = "season", description = "Temporada")
        Integer season,
        @JsonProperty("number")
        @Schema(name = "number", description = "Número do episódio")
        Integer number,
        @JsonProperty("type")
        @Schema(name = "type", description = "Tipo")
        String type,
        @JsonProperty("airdate")
        @Schema(name = "airdate", description = "Data de lançamento")
        String airdate,
        @JsonProperty("airtime")
        @Schema(name = "airtime", description = "Hora de lançamento")
        String airtime,
        @Schema(name = "airstamp", description = "Timestamp de lançamento")
        @JsonProperty("airstamp")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
        OffsetDateTime airstamp,
        @JsonProperty("runtime")
        @Schema(name = "runtime", description = "Duração")
        Integer runtime,
        @JsonProperty("rating")
        @Schema(name = "rating", description = "Nota")
        RatingDTO rating,
        @JsonProperty("summary")
        @Schema(name = "summary", description = "Resumo")
        String summary
) {}
