package com.hamzabekkaoui.cvcrudapp.controllers;

import com.hamzabekkaoui.cvcrudapp.entities.Experience;
import com.hamzabekkaoui.cvcrudapp.entities.Technology;
import com.hamzabekkaoui.cvcrudapp.services.ExperienceService;
import com.hamzabekkaoui.cvcrudapp.services.TechnologyService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/experiences")
public class ExperienceController {

    private final ExperienceService experienceService;

    public ExperienceController(ExperienceService technologyService) {
        this.experienceService = technologyService;
    }

    @GetMapping("/{id}")
    public Experience getExperienceById(@PathVariable int id) {
        try {
            return experienceService.getExperienceById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @GetMapping
    public List<Experience> getAll() {
        try {
            return experienceService.getAllExperiences();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping
    public Experience create(@RequestBody Experience experience) {
        try {
            return experienceService.save(experience);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{id}")
    public Experience update(@PathVariable int id , @RequestBody Experience experience) {
        try {
            return experienceService.update(id , experience);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    @DeleteMapping("/{id}")
    public Experience delete(@PathVariable int id) {
        try {
            return experienceService.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
