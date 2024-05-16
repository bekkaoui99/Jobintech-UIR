package com.hamzabekkaoui.cvcrudapp.services.impl;

import com.hamzabekkaoui.cvcrudapp.entities.Technology;
import com.hamzabekkaoui.cvcrudapp.repositories.TechnologyRepository;
import com.hamzabekkaoui.cvcrudapp.services.TechnologyService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class TechnologyServiceImpl implements TechnologyService {


    private final TechnologyRepository technologyRepository;

    public TechnologyServiceImpl(TechnologyRepository technologyRepository) {
        this.technologyRepository = technologyRepository;
    }


    @Override
    public Technology save(Technology technology) throws SQLException {
        return technologyRepository.save(technology);
    }

    @Override
    public Technology update(int id, Technology technology) throws SQLException {
        return technologyRepository.update(id, technology);
    }

    @Override
    public Technology delete(int id) throws SQLException {
        return technologyRepository.delete(id);
    }

    @Override
    public Technology getTechnologyById(int id) {
        return technologyRepository.findById(id);
    }

    @Override
    public List<Technology> getAllTechnologies() throws SQLException {
        return technologyRepository.findAll();
    }
}
