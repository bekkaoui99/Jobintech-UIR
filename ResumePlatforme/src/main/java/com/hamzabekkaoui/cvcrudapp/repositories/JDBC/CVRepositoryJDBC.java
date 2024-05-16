package com.hamzabekkaoui.cvcrudapp.repositories.JDBC;

import com.hamzabekkaoui.cvcrudapp.entities.CV;
import com.hamzabekkaoui.cvcrudapp.entities.Experience;
import com.hamzabekkaoui.cvcrudapp.entities.Information;
import com.hamzabekkaoui.cvcrudapp.entities.Technology;
import com.hamzabekkaoui.cvcrudapp.repositories.*;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CVRepositoryJDBC implements CvRepository {

    private final JDBCManager jdbcManager;
    private final InformationRepository informationRepository;
    private final TechnologyRepository technologyRepository;
    private final ExperienceRepository experienceRepository;

    public CVRepositoryJDBC(JDBCManager jdbcManager, InformationRepository informationRepository, TechnologyRepository technologyRepository, ExperienceRepository experienceRepository) {
        this.jdbcManager = jdbcManager;
        this.informationRepository = informationRepository;
        this.technologyRepository = technologyRepository;
        this.experienceRepository = experienceRepository;
    }

    @Override
    public CV save(CV cv) throws SQLException {
        Information information = informationRepository.save(cv.getInformation());
        System.out.println(information);
        String sqlForCvTable = "insert into cv(information_id) values(?)";
        int executed = jdbcManager.executeUpdate(true,sqlForCvTable, information.getId());
        if(executed > 0) {

            String sqlforGettingCVById = "select * from cv where id = ?";
            ResultSet getCvDataById = jdbcManager.executeQuery(sqlforGettingCVById, executed);

            if(getCvDataById.next()) {
                cv.setId(getCvDataById.getInt(1));
            }


            for(Technology technology : cv.getTechnologies()){
                Technology technologyByNameAndLevel = technologyRepository.findByNameAndLevel(technology.getName(), technology.getTechnologyLevel());

                String sqlForInsertDataToCV_TechnologyTable = "insert into cv_technology values(?,?)";
                int cv_technologyExecuted = jdbcManager.executeUpdate(false , sqlForInsertDataToCV_TechnologyTable, cv.getId(), technologyByNameAndLevel.getId());

                if(cv_technologyExecuted < 1) {
                    throw new RuntimeException("adding technology" + technology.getName() + " to Cv execution failed");
                }
            }

            for(Experience experience : cv.getExperiences()){

                Experience createdExperience = experienceRepository.save(experience);
                String sqlForInsertDataToCV_ExperienceTable = "insert into cv_experience values(?,?)";
                int cv_experienceExecuted = jdbcManager.executeUpdate(false , sqlForInsertDataToCV_ExperienceTable, cv.getId(), createdExperience.getId());

                if(cv_experienceExecuted < 1) {
                    throw new RuntimeException("adding experience" + createdExperience.getTitle() + " to Cv execution failed");
                }
            }
            return cv;
        }
        throw new RuntimeException("Insert failed");
    }

    @Override
    public CV update(int id, CV cv) {

        try {
            CV cvById = findById(id);

            if(cv.getInformation() != null){
                Information newInformation = cvById.getInformation();
                if(
                        newInformation.getFirstName() != null
                                &&  !newInformation.getFirstName().equals(cv.getInformation().getFirstName())
                ){
                    newInformation.setFirstName(cv.getInformation().getFirstName());
                }
                if(
                        newInformation.getLastName() != null
                                &&  !newInformation.getLastName().equals(cv.getInformation().getLastName())
                ){
                    newInformation.setLastName(cv.getInformation().getLastName());
                }
                Information updatedInformation = informationRepository.update(newInformation.getId(), newInformation);

            }

            if(cv.getTechnologies() != null){
                for(Technology technology : cv.getTechnologies()){
                    Technology newTechnology = new Technology();
                    if(technology.getTechnologyLevel() != null){
                     newTechnology.setTechnologyLevel(technology.getTechnologyLevel());
                    }
                    if(technology.getName() != null){
                        newTechnology.setName(technology.getName());
                    }
                    technologyRepository.update(technology.getId() , newTechnology);
                }
            }
            if(cv.getExperiences() != null){
                for(Experience experience : cv.getExperiences()){
                    Experience newExperience = new Experience();
                    if (experience.getTitle() != null){
                        newExperience.setTitle(experience.getTitle());
                    }
                    if(experience.getStart_date() != null){
                        newExperience.setStart_date(experience.getStart_date());
                    }
                    if(experience.getEnd_date() != null){
                        newExperience.setEnd_date(experience.getEnd_date());
                    }

                    experienceRepository.update(experience.getId() , newExperience);
                }
            }
            return findById(cvById.getId());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public CV delete(int id) {
        try {
            CV cvById = findById(id);
            String sqlForCv_technologyTable = "DELETE FROM cv_technology WHERE cv_id = ?";
            jdbcManager.executeUpdate(sqlForCv_technologyTable, cvById.getId());

            String sqlForCv_informationTable = "DELETE FROM cv_experience WHERE cv_id = ?";
            jdbcManager.executeUpdate(sqlForCv_informationTable, cvById.getId());

            String sqlForCvTable = "DELETE FROM cv WHERE id = ?";
            int executed = jdbcManager.executeUpdate(sqlForCvTable, cvById.getId());

            if(executed > 0) {
                String sqlForInformationTable = "delete from information where id = ?";
                jdbcManager.executeUpdate(sqlForInformationTable, cvById.getInformation().getId());
            return cvById;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return null;
    }

    private  List<Technology> findTechnologyByIDFromCv_TechnologyTable(int id) throws SQLException {
        List<Technology> technologies = new ArrayList<>();
        String sql =  "SELECT t.* " +
                "FROM technology t " +
                "INNER JOIN cv_technology ct ON t.id = ct.technology_id " +
                "WHERE ct.cv_id = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, id);
        if(resultSet.next()) {
            Technology technology = new Technology();
            technology.setId(resultSet.getInt("id"));
            technology.setName(resultSet.getString("name"));
            technology.setTechnologyLevel(resultSet.getString("technologyLevel"));
            technologies.add(technology);
        }
        return  technologies;
    }

    private  List<Experience> findExperienceByIDFromCv_ExperienceTable(int id) throws SQLException {
        List<Experience> experiences = new ArrayList<>();
        String sql = "SELECT e.*  FROM experience e INNER JOIN cv_experience ce ON e.id = ce.experience_id WHERE ce.cv_id = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, id);
        if(resultSet.next()) {
            Experience experience = new Experience();
            experience.setId(resultSet.getInt("id"));
            experience.setTitle(resultSet.getString("title"));
            experience.setStart_date(resultSet.getDate("start_date"));
            experience.setEnd_date(resultSet.getDate("end_date"));
            experiences.add(experience);
        }
        return  experiences;
    }

    @Override
    public CV findById(int id) throws SQLException {
        String sql = "select * from cv where id = ?";
        ResultSet resultSet = jdbcManager.executeQuery(sql, id);
        if(resultSet.next()) {
            CV cv = new CV();
            cv.setId(resultSet.getInt("id"));
            Information informationId = informationRepository.findById(resultSet.getInt("information_id"));
            cv.setInformation(informationId);
            List<Technology> technologyByIDFromCvTechnologyTable = findTechnologyByIDFromCv_TechnologyTable(cv.getId());
            cv.setTechnologies(technologyByIDFromCvTechnologyTable);
            List<Experience> experienceByIDFromCvExperienceTable = findExperienceByIDFromCv_ExperienceTable(cv.getId());
            cv.setExperiences(experienceByIDFromCvExperienceTable);
            return cv;
        }

        throw new RuntimeException("findById failed");
    }

    @Override
    public List<CV> getAll() throws SQLException {
        List<CV> cvs = new ArrayList<>();
        String sql = "select * from cv";
        ResultSet resultSet = jdbcManager.executeQuery(sql);
        if(resultSet.next()) {
            CV cv = new CV();
            cv.setId(resultSet.getInt("id"));
            Information informationId = informationRepository.findById(resultSet.getInt("information_id"));
            cv.setInformation(informationId);
            List<Technology> technologyByIDFromCvTechnologyTable = findTechnologyByIDFromCv_TechnologyTable(cv.getId());
            cv.setTechnologies(technologyByIDFromCvTechnologyTable);
            List<Experience> experienceByIDFromCvExperienceTable = findExperienceByIDFromCv_ExperienceTable(cv.getId());
            cv.setExperiences(experienceByIDFromCvExperienceTable);
            cvs.add(cv);
        }
        return cvs;
    }
}
