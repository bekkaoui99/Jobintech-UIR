package com.hamzabekkaoui.cvcrudapp.repositories.JDBC;

import com.hamzabekkaoui.cvcrudapp.entities.Technology;
import com.hamzabekkaoui.cvcrudapp.exception.ResourceNotFoundException;
import com.hamzabekkaoui.cvcrudapp.repositories.JDBCManager;
import com.hamzabekkaoui.cvcrudapp.repositories.TechnologyRepository;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TechnologyRepositoryJDBC implements TechnologyRepository {

    private final JDBCManager jdbcManager;

    public TechnologyRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }

    @Override
    public Technology findById(int id) {
        String sql = "SELECT * FROM technology WHERE id = ?";
        ResultSet resultSet = null;
        try {
            resultSet = jdbcManager.executeQuery(sql, id);
            Technology technology = new Technology();
            if (resultSet.next()) {
                technology.setId(resultSet.getInt("id"));
                technology.setName(resultSet.getString("name"));
                technology.setTechnologyLevel(resultSet.getString("technologyLevel"));
                return technology;
            }else {
                throw new ResourceNotFoundException("Technology with id " + id + " not found");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }




    }

    @Override
    public Technology findByNameAndLevel(String name , String level) throws SQLException {
        String sql = "SELECT * FROM technology WHERE name = ? AND technologyLevel = ? LIMIT 1";
        Technology getTechnology = new Technology();
        ResultSet getTechnologyByNameAndLevel = jdbcManager.executeQuery(sql, name , level);
        if(getTechnologyByNameAndLevel.next()) {
            getTechnology.setId(getTechnologyByNameAndLevel.getInt("id"));
            getTechnology.setName(getTechnologyByNameAndLevel.getString("name"));
            getTechnology.setTechnologyLevel(getTechnologyByNameAndLevel.getString("technologyLevel"));
            return getTechnology;
        }
        else {
            Technology technology = new Technology();
            technology.setName(name);
            technology.setTechnologyLevel(level);
            Technology createdTechnology = save(technology);
            return createdTechnology;
        }

    }

    @Override
    public List<Technology> findAll() throws SQLException {
        String sql = "select * from technology";
        List<Technology> technologies = new ArrayList<>();
        ResultSet resultSet = jdbcManager.executeQuery(sql);
        while (resultSet.next()){
            Technology technology = new Technology();
            technology.setId(resultSet.getInt("id"));
            technology.setName(resultSet.getString("name"));
            technology.setTechnologyLevel(resultSet.getString("technologyLevel"));
            technologies.add(technology);
        }
        return technologies;
    }

    @Override
    public Technology save(Technology technology) throws SQLException {
       String sql = "insert into technology (name, technologyLevel) values (?,?)";
        int createdTechnologyId = jdbcManager.executeUpdate(true,sql, technology.getName(), technology.getTechnologyLevel());

        if(createdTechnologyId > 0) {
          return findById(createdTechnologyId);
        }
        throw new RuntimeException("Technology could not be saved");
    }

    @Override
    public Technology delete(int id) throws SQLException {
        String deleteSQL = "DELETE FROM technology WHERE id = ?";
        int deletedTechnologyId = jdbcManager.executeUpdate(true, deleteSQL, id);
        if(deletedTechnologyId > 0) {
            return findById(deletedTechnologyId);
        }
        else {
            throw new ResourceNotFoundException("technology doesn't exist with id : " + id);
        }
    }

    @Override
    public Technology update(int id, Technology technology) throws SQLException {
        String updateSQL = "UPDATE technology SET name = ?, technologyLevel = ?  WHERE id = ?";
        int updatedTechnology = jdbcManager.executeUpdate( updateSQL, technology.getName(), technology.getTechnologyLevel() , id);
        if(updatedTechnology > 0) {
            return findById(id);
        }else {
            throw new ResourceNotFoundException("technology doesn't exist with name : " + technology.getName());
        }

    }
}
