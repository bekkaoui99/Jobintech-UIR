package com.hamzabekkaoui.cvcrudapp.services;

import com.hamzabekkaoui.cvcrudapp.entities.CV;

import java.sql.SQLException;
import java.util.List;

public interface CvService {

    CV save(CV cv) throws SQLException;
    CV update(int id , CV cv) throws SQLException;
    CV delete(int id) throws SQLException;
    CV getCVById(int id) throws SQLException;
    List<CV> getAllCV() throws SQLException;

}
