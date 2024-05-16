package com.hamzabekkaoui.cvcrudapp.controllers;

import com.hamzabekkaoui.cvcrudapp.entities.Technology;
import com.hamzabekkaoui.cvcrudapp.services.TechnologyService;
import lombok.Getter;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/technologies")
public class TechnologyController {

    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }


    @GetMapping("/{technologyId}")
    public Technology getTechnologyById(@PathVariable int technologyId) {

        return technologyService.getTechnologyById(technologyId);


    }

    @GetMapping
    public List<Technology> getTechnologies() {
        try {
            return technologyService.getAllTechnologies();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping()
    public Technology create(@RequestBody Technology technology) {
        try {
            return technologyService.save(technology);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @PutMapping("/{technologyId}")
    public Technology update(@PathVariable int technologyId , @RequestBody Technology technology) {
        try {
            return technologyService.update(technologyId , technology);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @DeleteMapping("/{technologyId}")
    public Technology delete(@PathVariable int technologyId) {
        try {
            return technologyService.delete(technologyId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



}
