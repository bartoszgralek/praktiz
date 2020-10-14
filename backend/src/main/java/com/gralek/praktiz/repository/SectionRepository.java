package com.gralek.praktiz.repository;

import com.gralek.praktiz.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SectionRepository extends JpaRepository<Section, Long> {
    Optional<Section> findByName(String name);
    Boolean existsByName(String name);
}
