package com.hamzabekkaoui.cvcrudapp.services;

import com.hamzabekkaoui.cvcrudapp.entities.CV;
import com.hamzabekkaoui.cvcrudapp.entities.Technology;

import java.sql.SQLException;
import java.util.List;

public interface TechnologyService {
    Technology save(Technology technology) throws SQLException;
    Technology update(int id , Technology technology) throws SQLException;
    Technology delete(int id) throws SQLException;
    Technology getTechnologyById(int id);
    List<Technology> getAllTechnologies() throws SQLException;

}
