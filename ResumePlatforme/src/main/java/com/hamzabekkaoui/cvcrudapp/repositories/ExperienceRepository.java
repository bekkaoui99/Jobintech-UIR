package com.hamzabekkaoui.cvcrudapp.repositories;

import com.hamzabekkaoui.cvcrudapp.entities.Experience;
import com.hamzabekkaoui.cvcrudapp.entities.Technology;

import java.sql.SQLException;
import java.util.List;

public interface ExperienceRepository {
    Experience findById(int id) throws SQLException;
    List<Experience> findAll() throws SQLException;
    Experience save(Experience experience) throws SQLException;
    Experience delete(int id) throws SQLException;
    Experience update(int id , Experience experience) throws SQLException;
}
