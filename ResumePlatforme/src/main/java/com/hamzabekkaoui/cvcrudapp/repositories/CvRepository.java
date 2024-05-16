package com.hamzabekkaoui.cvcrudapp.repositories;

import com.hamzabekkaoui.cvcrudapp.entities.CV;

import java.sql.SQLException;
import java.util.List;

public interface CvRepository {

    CV save(CV cv) throws SQLException;
    CV update(int id , CV cv);
    CV delete(int id);
    CV findById(int id) throws SQLException;
    List<CV> getAll() throws SQLException;

}
