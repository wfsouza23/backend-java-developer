package com.cmanager.app.application.data;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ShowResponseDTO", description = "Objeto da representação de Shows")
@JsonIgnoreProperties(ignoreUnknown = true)
public record ShowResponseDTO(

        @JsonProperty("idIntegration")
        @Schema(name = "idIntegration", description = "Id externo de integração do show")
        String idIntegration,

        @JsonProperty("name")
        @Schema(name = "name", description = "Nome do show")
        String name,

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

        @JsonProperty("summary")
        @Schema(name = "summary", description = "Resumo do show")
        String summary
) {}
