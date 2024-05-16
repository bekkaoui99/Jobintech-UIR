package com.hamzabekkaoui.cvcrudapp.entities;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CV {

    private int id;
    private List<Experience> experiences;
    private List<Technology> technologies;
    private Information information;

}
