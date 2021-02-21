package com.gralek.praktiz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class DayConfigurator {

    @Getter
    DaySection[][] daySections;

    private final ObjectMapper mapper;

    public DayConfigurator(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    void init() throws IOException {
        String json = Files.readString(Path.of("backend\\schedule_config.json"));
        daySections = mapper.readValue(json, DaySection[][].class);
    }
}
