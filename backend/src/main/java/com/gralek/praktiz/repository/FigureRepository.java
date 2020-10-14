package com.gralek.praktiz.repository;

import com.gralek.praktiz.model.Figure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface FigureRepository extends JpaRepository<Figure, Long> {

    Set<Figure> findFiguresByParent(Figure parent);
}
