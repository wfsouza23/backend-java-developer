package com.cmanager.app.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class TvMazeEpisodeDTO {

    private Long id;
    private String name;
    private Integer season;
    private Integer number;
    private String type;
    private LocalDate airdate;
    private String airtime;
    private Integer runtime;
    private Rating rating;
    private String summary;


    @Getter
    @Setter
    public static class Rating {
        private BigDecimal average;
    }
}

