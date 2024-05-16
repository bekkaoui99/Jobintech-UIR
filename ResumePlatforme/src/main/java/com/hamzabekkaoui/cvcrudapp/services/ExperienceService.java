package com.hamzabekkaoui.cvcrudapp.services;

import com.hamzabekkaoui.cvcrudapp.entities.Experience;
import com.hamzabekkaoui.cvcrudapp.entities.Technology;

import java.sql.SQLException;
import java.util.List;

public interface ExperienceService {
    Experience save(Experience experience) throws SQLException;
    Experience update(int id , Experience experience) throws SQLException;
    Experience delete(int id) throws SQLException;
    Experience getExperienceById(int id) throws SQLException;
    List<Experience> getAllExperiences() throws SQLException;

}
