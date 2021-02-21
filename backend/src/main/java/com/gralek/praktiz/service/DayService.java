package com.gralek.praktiz.service;

import com.gralek.praktiz.config.DayConfigurator;
import com.gralek.praktiz.config.DaySection;
import com.gralek.praktiz.model.Section;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayService {

    private final DayConfigurator configurator;
    private final SectionService sectionService;

    public DayService(DayConfigurator configurator, SectionService sectionService) {
        this.configurator = configurator;
        this.sectionService = sectionService;
    }

    public List<Section> getTodaySections() {

        LocalDate date = LocalDate.now();
        DayOfWeek dayOfWeek = date.getDayOfWeek();

        DaySection[] daySections = configurator.getDaySections()[dayOfWeek.getValue() - 1];
        return Arrays.stream(daySections)
                .map(daySection -> sectionService.getRandomFigures(daySection.getSection(), daySection.getLimit()))
                .collect(Collectors.toList());
    }

}
