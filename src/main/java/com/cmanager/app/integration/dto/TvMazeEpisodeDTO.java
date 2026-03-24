package com.cmanager.app.integration.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

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
    private RatingDTO rating;
    private String summary;

}

