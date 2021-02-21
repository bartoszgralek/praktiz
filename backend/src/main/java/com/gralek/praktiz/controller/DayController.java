package com.gralek.praktiz.controller;

import com.gralek.praktiz.service.DayService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/today")
public class DayController {

    private final DayService dayService;

    public DayController(DayService dayService) {
        this.dayService = dayService;
    }

    @GetMapping
    public ResponseEntity<?> getTodaySections() {
        return ResponseEntity.ok(dayService.getTodaySections());
    }
}
