package com.gralek.praktiz.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TodayTask{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    LocalDate localDate;

    @OneToMany
    private List<Figure> figures = new ArrayList<>();
}
