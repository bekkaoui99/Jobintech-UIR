package com.hamzabekkaoui.cvcrudapp.repositories;

import com.hamzabekkaoui.cvcrudapp.entities.CV;
import com.hamzabekkaoui.cvcrudapp.entities.Information;

import java.sql.SQLException;
import java.util.List;

public interface InformationRepository {

    Information save(Information information) throws SQLException;
    Information update(int id , Information information) throws SQLException;
    Information delete(int id) throws SQLException;
    Information findById(int id) throws SQLException;;
    List<Information> getAll() throws SQLException;

}
