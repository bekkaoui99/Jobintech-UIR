package com.hamzabekkaoui.cvcrudapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Technology {

    private int id;
    private String name;
    private String technologyLevel;
    private List<CV> cvs;
}
