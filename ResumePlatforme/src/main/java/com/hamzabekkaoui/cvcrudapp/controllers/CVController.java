package com.hamzabekkaoui.cvcrudapp.controllers;

import com.hamzabekkaoui.cvcrudapp.entities.CV;
import com.hamzabekkaoui.cvcrudapp.services.CvService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cv")
public class CVController {

    private final CvService cvService;


    public CVController(CvService cvService) {
        this.cvService = cvService;
    }


    @PostMapping
    public CV create(@RequestBody CV cv) {
        try {
            return cvService.save(cv);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/{id}")
    public CV getCv(@PathVariable int id) {
        try {
            return cvService.getCVById(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public List<CV> getAllCv() {
        try {
            return cvService.getAllCV();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public CV delete(@PathVariable int id) {
        try {
            return cvService.delete(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("/{id}")
    public CV update(@PathVariable int id , @RequestBody CV cv) {
        try {
            return cvService.update(id , cv);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
