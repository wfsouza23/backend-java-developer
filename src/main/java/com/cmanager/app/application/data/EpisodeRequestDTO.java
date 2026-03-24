package com.cmanager.app.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Schema(name = "EpisodeRequestDTO", description = "Request para criação/atualização de episódios")
@JsonIgnoreProperties(ignoreUnknown = true)
public record EpisodeRequestDTO(

        @JsonProperty("idIntegration")
        @Schema(name = "idIntegration", description = "Id externo de integração do episódio")
        String idIntegration,

        @JsonProperty("showId")
        @Schema(name = "showId", description = "Id do show ao qual o episódio pertence")
        String showId,

        @JsonProperty("name")
        @Schema(name = "name", description = "Nome do episódio")
        String name,

        @JsonProperty("season")
        @Schema(name = "season", description = "Número da temporada")
        Integer season,

        @JsonProperty("number")
        @Schema(name = "number", description = "Número do episódio dentro da temporada")
        Integer number,

        @JsonProperty("type")
        @Schema(name = "type", description = "Tipo do episódio (regular, special, etc.)")
        String type,

        @JsonProperty("airdate")
        @Schema(name = "airdate", description = "Data de exibição do episódio")
        LocalDate airdate,

        @JsonProperty("airtime")
        @Schema(name = "airtime", description = "Horário de exibição")
        String airtime,

        @JsonProperty("airstamp")
        @Schema(name = "airstamp", description = "Data e hora completa de exibição")
        LocalDateTime airstamp,

        @JsonProperty("runtime")
        @Schema(name = "runtime", description = "Duração do episódio em minutos")
        Integer runtime,

        @JsonProperty("rating")
        @Schema(name = "rating", description = "Nota média do episódio")
        BigDecimal rating,

        @JsonProperty("summary")
        @Schema(name = "summary", description = "Resumo do episódio")
        String summary

) {}
