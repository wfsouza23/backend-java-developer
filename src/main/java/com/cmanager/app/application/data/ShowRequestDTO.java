package com.cmanager.app.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "ShowRequestDTO", description = "Request para criação/atualização de Shows")
@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowRequestDTO(

        @JsonProperty("idIntegration")
        @Schema(name = "idIntegration", description = "Id externo de integração do show")
        String idIntegration,

        @JsonProperty("name")
        @Schema(name = "name", description = "Nome do show")
        String name,

        @JsonProperty("type")
        @Schema(name = "type", description = "Tipo do show (ex.: Scripted, Reality, etc.)")
        String type,

        @JsonProperty("language")
        @Schema(name = "language", description = "Idioma principal do show")
        String language,

        @JsonProperty("status")
        @Schema(name = "status", description = "Status atual do show (ex.: Running, Ended)")
        String status,

        @JsonProperty("runtime")
        @Schema(name = "runtime", description = "Duração típica de um episódio em minutos")
        Integer runtime,

        @JsonProperty("averageRuntime")
        @Schema(name = "averageRuntime", description = "Duração média dos episódios em minutos")
        Integer averageRuntime,

        @JsonProperty("officialSite")
        @Schema(name = "officialSite", description = "Site oficial do show")
        String officialSite,

        @JsonProperty("rating")
        @Schema(name = "rating", description = "Nota média do show")
        BigDecimal rating,

        @JsonProperty("summary")
        @Schema(name = "summary", description = "Resumo do show")
        String summary
) {}
