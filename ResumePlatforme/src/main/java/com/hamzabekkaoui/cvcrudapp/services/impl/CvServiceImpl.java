package com.hamzabekkaoui.cvcrudapp.services.impl;

import com.hamzabekkaoui.cvcrudapp.entities.CV;
import com.hamzabekkaoui.cvcrudapp.repositories.CvRepository;
import com.hamzabekkaoui.cvcrudapp.repositories.JDBCManager;
import com.hamzabekkaoui.cvcrudapp.services.CvService;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;


@Service
public class CvServiceImpl implements CvService {

    private final CvRepository cvRepository;

    public CvServiceImpl(CvRepository cvRepository) {
        this.cvRepository = cvRepository;

    }

    @Override
    public CV save(CV cv) throws SQLException {
        return cvRepository.save(cv);
    }

    @Override
    public CV update(int id, CV cv) throws SQLException {
        return cvRepository.update(id, cv);
    }

    @Override
    public CV delete(int id) throws SQLException  {
        return cvRepository.delete(id);
    }

    @Override
    public CV getCVById(int id) throws SQLException {
        return cvRepository.findById(id);
    }

    @Override
    public List<CV> getAllCV() throws SQLException {
        return cvRepository.getAll();
    }
}
