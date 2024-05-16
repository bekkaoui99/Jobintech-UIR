package com.hamzabekkaoui.cvcrudapp.repositories;

import com.hamzabekkaoui.cvcrudapp.entities.Technology;

import java.sql.SQLException;
import java.util.List;

public interface TechnologyRepository {
    Technology findById(int id);
    List<Technology> findAll() throws SQLException;
    Technology save(Technology technology) throws SQLException;
    Technology delete(int id) throws SQLException;
    Technology update(int id , Technology technology) throws SQLException;
    Technology findByNameAndLevel(String name , String level) throws SQLException;
}
