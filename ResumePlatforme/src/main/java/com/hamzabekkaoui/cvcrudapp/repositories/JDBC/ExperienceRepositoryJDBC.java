package com.hamzabekkaoui.cvcrudapp.repositories.JDBC;

import com.hamzabekkaoui.cvcrudapp.entities.Experience;
import com.hamzabekkaoui.cvcrudapp.repositories.ExperienceRepository;
import com.hamzabekkaoui.cvcrudapp.repositories.JDBCManager;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExperienceRepositoryJDBC implements ExperienceRepository {

    private final JDBCManager jdbcManager;

    public ExperienceRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }

    @Override
    public Experience findById(int id) throws SQLException {
        String sql = "select * from experience where id = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, id);
        Experience experience = new Experience();
        if(resultSet.next()) {
            experience.setId(resultSet.getInt("id"));
            experience.setTitle(resultSet.getString("title"));
            experience.setStart_date(resultSet.getDate("start_date"));
            experience.setEnd_date(resultSet.getDate("end_date"));
            return experience;
        }else {
            throw new RuntimeException("Experience not found");
        }

    }

    @Override
    public List<Experience> findAll() throws SQLException {
        String sql = "select * from experience";
        ResultSet resultSet = jdbcManager.executeQuery(sql);
        List<Experience> experiences = new ArrayList<Experience>();
        if(resultSet.next()) {
            Experience experience = new Experience();
            experience.setId(resultSet.getInt("id"));
            experience.setTitle(resultSet.getString("title"));
            experience.setStart_date(resultSet.getDate("start_date"));
            experience.setEnd_date(resultSet.getDate("end_date"));
            experiences.add(experience);
        }
        return experiences;
    }

    @Override
    public Experience save(Experience experience) throws SQLException {
        String sql = "insert into experience (title ,start_date,end_date)  VALUES (?,?,?)";
        int createdExperienceId = jdbcManager.executeUpdate(true , sql, experience.getTitle(), experience.getStart_date(), experience.getEnd_date());
        if(createdExperienceId > 0) {
            return findById(createdExperienceId);
        }
        throw new RuntimeException("Error inserting experience");

    }

    @Override
    public Experience delete(int id) throws SQLException {
        String deleteSQL = "DELETE FROM experience WHERE id = ?";
        int deletedExperienceId = jdbcManager.executeUpdate(true, deleteSQL, id);
        if(deletedExperienceId > 0) {
            return findById(deletedExperienceId);
        }
        throw new RuntimeException("Error deleting experience");
    }

    @Override
    public Experience update(int id, Experience experience) throws SQLException {
        String updateSQL = "UPDATE experience SET title = ?, start_date = ?, end_date = ? WHERE id = ?";
        int updatedExperience = jdbcManager.executeUpdate( updateSQL, experience.getTitle(), experience.getStart_date(), experience.getEnd_date(), id);
        if(updatedExperience > 0) {
            return findById(id);
        }
        throw new RuntimeException("Error updating experience");
    }
}
