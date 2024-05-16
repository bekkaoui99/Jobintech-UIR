package com.hamzabekkaoui.cvcrudapp.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Experience {

    private int id;
    private String title;
    private Date start_date;
    private Date end_date;
    private List<CV> cvs;
}
