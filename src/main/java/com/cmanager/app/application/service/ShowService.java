package com.cmanager.app.application.service;

import com.cmanager.app.application.domain.Show;
import com.cmanager.app.application.repository.ShowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShowService {

    private final ShowRepository showRepository;

    public ShowService(ShowRepository showRepository) {
        this.showRepository = showRepository;
    }

    public Show save(Show show) {
        return showRepository.save(show);
    }

    public List<Show> findAll() {
        return showRepository.findAll();
    }

    public Show findById(String id) {
        return showRepository.findById(id).orElse(null);
    }
}
