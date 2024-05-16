package com.hamzabekkaoui.cvcrudapp.services.impl;

import com.hamzabekkaoui.cvcrudapp.entities.Experience;
import com.hamzabekkaoui.cvcrudapp.entities.Technology;
import com.hamzabekkaoui.cvcrudapp.repositories.ExperienceRepository;
import com.hamzabekkaoui.cvcrudapp.repositories.TechnologyRepository;
import com.hamzabekkaoui.cvcrudapp.services.ExperienceService;
import com.hamzabekkaoui.cvcrudapp.services.TechnologyService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class ExperienceServiceImpl implements ExperienceService {

    private final ExperienceRepository experienceRepository;

    public ExperienceServiceImpl(ExperienceRepository experienceRepository) {
        this.experienceRepository = experienceRepository;
    }

    @Override
    public Experience save(Experience experience) throws SQLException {
        return experienceRepository.save(experience);
    }

    @Override
    public Experience update(int id, Experience experience) throws SQLException {
        return experienceRepository.update(id, experience);
    }

    @Override
    public Experience delete(int id) throws SQLException {
        return experienceRepository.delete(id);
    }

    @Override
    public Experience getExperienceById(int id) throws SQLException {
        return experienceRepository.findById(id);
    }

    @Override
    public List<Experience> getAllExperiences() throws SQLException {
        return experienceRepository.findAll();
    }


}
