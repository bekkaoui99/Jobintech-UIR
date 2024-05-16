package com.hamzabekkaoui.cvcrudapp.repositories.JDBC;

import com.hamzabekkaoui.cvcrudapp.entities.Information;
import com.hamzabekkaoui.cvcrudapp.repositories.InformationRepository;
import com.hamzabekkaoui.cvcrudapp.repositories.JDBCManager;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class InformationRepositoryJDBC implements InformationRepository {


    private final JDBCManager jdbcManager;

    public InformationRepositoryJDBC(JDBCManager jdbcManager) {
        this.jdbcManager = jdbcManager;
    }

    @Override
    public Information save(Information information) throws SQLException {
        String sql = "insert into information (firstName, lastName) VALUES (?,?)";
        int createdInformationId = jdbcManager.executeUpdate(true,sql, information.getFirstName(), information.getLastName());

        if(createdInformationId > 0) {
           return findById(createdInformationId);
        }
        throw new RuntimeException("Error inserting information");
    }

    @Override
    public Information update(int id, Information information) throws SQLException {
        String updateSQL = "UPDATE information SET firstName = ?, lastName = ?  WHERE id = ?";
        int updatedInformation = jdbcManager.executeUpdate( updateSQL, information.getFirstName(), information.getLastName() , id);
        if(updatedInformation > 0) {
            return findById(id);
        }
        throw new RuntimeException("Error updating Information data");
    }

    @Override
    public Information delete(int id) throws SQLException {
        String deleteSQL = "DELETE FROM information WHERE id = ?";
        int deletedInformationId = jdbcManager.executeUpdate(true, deleteSQL, id);
        if(deletedInformationId > 0) {
            return findById(deletedInformationId);
        }
        throw new RuntimeException("Error deleting Information data");
    }

    @Override
    public Information findById(int id) throws SQLException {
        String sql = "SELECT * FROM information WHERE id = ?";
        Information information = new Information();
        ResultSet resultSet = jdbcManager.executeQuery(sql, id);
        if(resultSet.next()) {
            information.setId(resultSet.getInt("id"));
            information.setFirstName(resultSet.getString("firstName"));
            information.setLastName(resultSet.getString("lastName"));
            return information;
        }
        throw new RuntimeException("not found experience");
    }


    @Override
    public List<Information> getAll() throws SQLException {
        String sql = "SELECT * FROM information";
        List<Information> informationList = new ArrayList<Information>();
        ResultSet resultSet = jdbcManager.executeQuery(sql);
        while(resultSet.next()) {
            Information information = new Information();
            information.setId(resultSet.getInt("id"));
            information.setFirstName(resultSet.getString("firstName"));
            information.setLastName(resultSet.getString("lastName"));
            informationList.add(information);
        }

        return informationList;
    }
}
