package com.gralek.praktiz.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component
public class DayConfigurator {

    @Getter
    DaySection[][] daySections;

    private final ObjectMapper mapper;

    public DayConfigurator(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @PostConstruct
    void init() throws IOException, URISyntaxException {
        URL res = DayConfigurator.class.getClassLoader().getResource("schedule_config.json");
        String json = Files.readString(Paths.get(res.toURI()));
        daySections = mapper.readValue(json, DaySection[][].class);
    }
}
