package com.gralek.praktiz.service;

import com.gralek.praktiz.config.DayConfigurator;
import com.gralek.praktiz.config.DaySection;
import com.gralek.praktiz.model.Figure;
import com.gralek.praktiz.model.TodayTask;
import com.gralek.praktiz.repository.TodayTaskRepository;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DayService {

    private final DayConfigurator configurator;
    private final SectionService sectionService;
    private final TodayTaskRepository todayTaskRepository;

    public DayService(DayConfigurator configurator, SectionService sectionService, TodayTaskRepository todayTaskRepository) {
        this.configurator = configurator;
        this.sectionService = sectionService;
        this.todayTaskRepository = todayTaskRepository;
    }

    public TodayTask getTodayTask() {

        LocalDate date = LocalDate.now();

        return todayTaskRepository.findFirstByLocalDate(date)
            .orElseGet(() -> {
                DayOfWeek dayOfWeek = date.getDayOfWeek();
                DaySection[] daySections = configurator.getDaySections()[dayOfWeek.getValue() - 1];
                List<Figure> figures = Arrays.stream(daySections)
                        .map(daySection -> sectionService.getRandomFigures(daySection.getSection(), daySection.getLimit()))
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
                TodayTask todayTask = new TodayTask();
                todayTask.setLocalDate(date);
                todayTask.setFigures(figures);
                return todayTaskRepository.save(todayTask);
            });

    }

}
